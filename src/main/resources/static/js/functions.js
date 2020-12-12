function logout() {
    // document.getElementById('login-nav').setAttribute('hidden', false);
    document.getElementById('account-nav').setAttribute('hidden', true);
    document.getElementById('login-nav').removeAttribute('hidden', true);

}// terminate session and set the login btn active/logout btn hidden

function on_load() {
    document.getElementById('logout_btn').setAttribute('hidden', true);
}// if no session turn off logout btn else turn off login btn

function login_switch() {
    // managing buttons 
    document.getElementById('login_btn').removeAttribute('hidden', true);
    document.getElementById('sing_up_btn').setAttribute('hidden', true);
    // managing input
    document.getElementById('repeat_password').setAttribute('hidden', true);
    document.getElementById('repeat_password').removeAttribute('required', true);
    // managing switches
    document.getElementById('login_btn_switch').classList.add('active');
    document.getElementById('sign_up_btn_switch').classList.remove('active');
}

function sign_up_switch() {
    // managing buttons
    document.getElementById('sing_up_btn').removeAttribute('hidden', true);
    document.getElementById('login_btn').setAttribute('hidden', true);
    // managing input
    document.getElementById('repeat_password').removeAttribute('hidden', true);
    document.getElementById('repeat_password').setAttribute('required', true);
    // managing switches
    document.getElementById('login_btn_switch').classList.remove('active');
    document.getElementById('sign_up_btn_switch').classList.add('active');
}

function validate() {
    // var passwd = document.getElementById('')
    
    
}
