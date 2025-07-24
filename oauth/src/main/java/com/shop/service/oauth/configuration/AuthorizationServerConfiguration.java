package com.shop.service.oauth.configuration;

import com.alibaba.fastjson.JSONObject;
import com.shop.service.oauth.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
    @Autowired
    AuthenticationManager authenticationManager;



//    设置资源服务器id
    private static final String DEMO_RESOURCE_ID = "order";



    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // password 持多种编码，通过密码的前缀区分编码方式
        String finalSecret = "{bcrypt}" + new BCryptPasswordEncoder().encode("123456");
        //配置两个客户端,一个用于password认证一个用于client认证

        //client模式，没有用户的概念，直接与认证服务器交互，用配置中的客户端信息去申请accessToken，
        // 客户端有自己的client_id,client_secret对应于用户的username,password，而客户端也拥有自己的authorities，
        // 当采取client模式认证时，对应的权限也就是客户端自己的authorities
        clients.inMemory().withClient("client_1")
                .resourceIds(DEMO_RESOURCE_ID)
                .authorizedGrantTypes("client_credentials", "refresh_token")
                .scopes("client_1")
                .authorities("ROLE_ADMIN")
                .secret(finalSecret)

                //password模式，自己本身有一套用户体系，在认证时需要带上自己的用户名和密码，以及客户端的client_id,client_secret
                // 此时，accessToken所包含的权限是用户本身的权限，而不是客户端的权限
                .and().withClient("client_2")
                .resourceIds(DEMO_RESOURCE_ID)
                .authorizedGrantTypes("password", "refresh_token")
                .scopes("client_2")
                .secret(finalSecret);
    }


    /**
     * 定义token存放位置和token的内容
     * @return
     */
    @Bean
    public TokenStore tokenStore() {
//        return new JdbcTokenStore(dataSource);
        return new JwtTokenStore(accessTokenConverter());
    }

    /**
     * 生成jwttoken的内容
     * @return
     */
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter(){
            /***
			 * 重写增强token方法,用于自定义一些token返回的信息
			 */
			@Override
			public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
			    try{
			        //将登陆时获取的用户信息注入到jwt数据中这样可以脱离session采用无状态服务同步用户数据
                    String userName = authentication.getUserAuthentication().getName();
                    UserEntity user = (UserEntity) authentication.getUserAuthentication().getPrincipal();// 与登录时候放进去的UserDetail实现类一直查看link{SecurityConfiguration}
                    System.out.println(user);
                    /** 自定义一些token属性 ***/
                    final Map<String, Object> additionalInformation = new HashMap<>();
                    additionalInformation.put("userInfo", JSONObject.toJSONString(user));
                    ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInformation);
                    OAuth2AccessToken enhancedToken = super.enhance(accessToken, authentication);
                    return enhancedToken;
                }catch (Exception e){
			        e.printStackTrace();
//			        这个异常处理主要解决的是使用client模式授权时由于没有用户上面的部分会报错，所以采用直接处理token信息的方式
                    OAuth2AccessToken enhancedToken = super.enhance(accessToken, authentication);
			        return enhancedToken;
                }


			}
        };
//        设置jwt的盐值，这里采用对称加密简化难度，不实用rsa模式
        converter.setSigningKey("123");

        return converter;
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {

        oauthServer

                // 允许客户表单认证,不加的话/oauth/token无法访问
                .allowFormAuthenticationForClients()
                // 对于CheckEndpoint控制器[框架自带的校验]的/oauth/token端点允许所有客户端发送器请求而不会被Spring-security拦截
                // 开启/oauth/token_key验证端口无权限访问
                .tokenKeyAccess("permitAll()")
                // 要访问/oauth/check_token必须设置为permitAll()，但这样所有人都可以访问了，设为isAuthenticated()又导致访问不了，这个问题暂时没找到解决方案
                // 开启/oauth/check_token验证端口认证权限访问
                .checkTokenAccess("permitAll()");
    }

    //定义授权和令牌端点以及令牌服务
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // redisTokenStore
//        endpoints.tokenStore(new RedisTokenStore(redisConnectionFactory))
//                .authenticationManager(authenticationManager)
//                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);

        endpoints
                //指定token存储位置
                .tokenStore(tokenStore())
                // 配置JwtAccessToken转换器
                .tokenEnhancer(accessTokenConverter())
                //指定认证管理器
                .authenticationManager(authenticationManager)
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
        ;
//        System.out.println("-------------");
        // 配置tokenServices参数
        DefaultTokenServices tokenServices = new DefaultTokenServices();
//        System.out.println(tokenServices);
        tokenServices.setTokenStore(endpoints.getTokenStore());
        // 是否支持刷新
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setClientDetailsService(endpoints.getClientDetailsService());
        tokenServices.setTokenEnhancer(endpoints.getTokenEnhancer());
        // 20分钟
//        tokenServices.setAccessTokenValiditySeconds((int) TimeUnit.MINUTES.toSeconds(200));
        endpoints.tokenServices(tokenServices);
    }

}

