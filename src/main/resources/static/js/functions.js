function logout(){
    // document.getElementById('login-nav').setAttribute('hidden', false);
    document.getElementById('account-nav').setAttribute('hidden', true);
    document.getElementById('login-nav').removeAttribute('hidden', true);

}// terminate session and set the login btn active/logout btn hidden

function on_load(){
    document.getElementById('logout_btn').setAttribute('hidden', true);
}// if no session turn off logout btn else turn off login btn

function login_switch(){
    document.getElementById('signup_form').setAttribute('hidden', true);
    document.getElementById('login_form').removeAttribute('hidden', true);
    document.getElementById('login_btn_swith').classList.add('active');
    document.getElementById('signup_btn_swith').classList.remove('active');
}

function signup_switch(){
    document.getElementById('login_form').setAttribute('hidden', true);
    document.getElementById('signup_form').removeAttribute('hidden', true);
    document.getElementById('login_btn_swith').classList.remove('active');
    document.getElementById('signup_btn_swith').classList.add('active');
}