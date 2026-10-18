package oit.is.z3694.kaizi.janken.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class JankenAuthConfiguration {
  /**
   * 認可処理に関する設定（認証されたユーザがどこにアクセスできるか）
   *
   * @param http
   * @return
   * @throws Exception
   */
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.formLogin(login -> login
        .permitAll())
        .logout(logout -> logout
            .logoutUrl("/logout")
            .logoutSuccessUrl("/")) // ログアウト後にトップページ (/) にリダイレクト
        .authorizeHttpRequests(authz -> authz
            .requestMatchers("/janken/**").authenticated() // /janken以下は認証が必要
            .anyRequest().permitAll()); // 上記以外（トップページ等）は全員アクセス可能

    return http.build();
  }

  /**
   * 認証処理に関する設定（誰がどのようなロールでログインできるか）
   *
   * @return
   */
  @Bean
  public InMemoryUserDetailsManager userDetailsService() {

    // ユーザ名，パスワード，ロールを指定してbuildする
    // このときパスワードはBCryptでハッシュ化されているため，{bcrypt}とつける
    // ハッシュ化せずに平文でパスワードを指定する場合は{noop}をつける

    UserDetails user1 = User.withUsername("user1")
        .password("{bcrypt}$2y$05$7l6hcvgCKbMZ5gQ8xlCTtO3m5kd2tAS9zGppcGQwJYPb6ohjGQZte").roles("USER").build();// isdev
    UserDetails user2 = User.withUsername("user2")
        .password("{bcrypt}$2y$05$7l6hcvgCKbMZ5gQ8xlCTtO3m5kd2tAS9zGppcGQwJYPb6ohjGQZte").roles("USER").build();// isdev
    // UserDetails admin = User.withUsername("admin")
    // .password("{bcrypt}$2y$05$VCivUnmJLU9R96BZZHXl/u.0SFcFvWZJm/36yGZMk/Hu0zPUNAGHy").roles("ADMIN").build();//9012

    // 生成したユーザをImMemoryUserDetailsManagerに渡す（いくつでも良い）
    return new InMemoryUserDetailsManager(user1, user2);
  }

}
