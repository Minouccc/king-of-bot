<template>
    <ContentFieldVue>
        <div style="display: flex;justify-content: center;">
            <img class="logo" src="../../../assets//images/logo.png" />
        </div>
        <div class="row  justify-content-md-center" style="margin-top: 3px;">登录用户中心</div>
        <div class="row  justify-content-md-center" style="margin-top: 10px;">
            <div class="col-3">
                <!--submit时触发register函数 并阻止默认行为-->
                <form @submit.prevent="register">
                    <div class="mb-3">
                        <label for="username" class="form-label">用户名</label>
                        <input v-model="username" type="text" class="form-control" id="username" placeholder="请输入用户名">
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">密码</label>
                        <input v-model="password" type="password" class="form-control" id="password"
                            placeholder="请输入密码">
                    </div>
                    <div class="mb-3">
                        <label for="confirmedPassword" class="form-label">确认密码</label>
                        <input v-model="confirmedPassword" type="password" class="form-control" id="confirmedPassword"
                            placeholder="请再次输入密码">
                    </div>
                    <div class="error_message">{{ error_message }}</div>
                    <button type="submit" class="btn btn-primary">注册</button>
                </form>
            </div>
        </div>
        <div class="register-wrap">
            <span style="padding-right: 5px;">已经有账号？</span>
            <span class="register" @click="onChangeRouter">立即登录</span>
        </div>
    </ContentFieldVue>
</template>
<script>
import $ from 'jquery'
import ContentFieldVue from '../../../components/ContentField.vue'
import { ref } from 'vue'
import router from '../../../router/index'

export default {
    components: {
        ContentFieldVue
    },
    setup() {
        let username = ref('');
        let password = ref('');
        let confirmedPassword = ref('');
        let error_message = ref('');

        const register = () => {
            error_message.value = ""
            $.ajax({
                url: "http://127.0.0.1:3000/user/account/register/",
                type: "post",
                data: {
                    username: username.value,
                    password: password.value,
                    confirmedPassword: confirmedPassword.value
                },
                success(resp) {
                    if (resp.message === "success") {
                        router.push({name: "user_account_login"});
                    }else{
                       error_message.value = resp.message;//如果注册失败, 显示错误信息
                    }
                }
            });
        }
        const onChangeRouter = () => {
            router.push({
                name: "user_account_login",
            })
        }
        return {
            username,
            password,
            confirmedPassword,
            error_message,
            register,
            onChangeRouter
        }
    }

}
</script>
<style scoped>
.logo {
    display: inline-block;
    width: 70px;
    height: 70px;
}
.register-wrap {
    display: flex;
    justify-content: center;
    margin-top: 10px;
    color: #808080;
    font-size: 12px;
}

.register {
    display: inline-block;
    cursor: pointer;
}

.register:hover {
    color: #000;
}
button {
    width: 100%;
}
div.error_message{
    color: red;
}
</style>