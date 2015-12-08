<#include "header.ftl" />

<p>SingIn</p>

    <form name="loginForm" method="post" action="/auth_validate">
        <input type="text" name="username" placeholder="username" />
        <input type="password" name="password" placeholder="password" />
        <input name="submit" type="submit" value="Login" />
    </form>

<#include "footer.ftl" />