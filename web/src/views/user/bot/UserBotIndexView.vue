<template>
    <div class="container">
        <div class="row">
            <div class="col-3">
                <div class="card">
                    <div class="card-body">
                        <img :src="$store.state.user.photo" style="width:100%">
                    </div>
                    <div
                        style="display: flex; justify-content: space-around; background-color: rgba(128, 128, 128, 0.3);">
                        <div style="text-align: center;">
                            <div>{{ follows.follower_count || 0 }}</div>
                            <div>关注</div>
                        </div>
                        <div style="text-align: center;">
                            <div>{{ follows.fan_count || 0}}</div>
                            <div>粉丝</div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-9">
                <div class="card">
                    <div class="card-header d-flex align-items-center justify-content-between">
                        <span style="font-size:22px">我的Bot</span>
                        <!-- Button trigger modal -->
                        <button type="button" class="btn btn-primary" data-bs-toggle="modal"
                            data-bs-target="#add-bot-btn">
                            创建
                        </button>

                        <!-- Modal -->
                        <div class="modal fade " id="add-bot-btn" tabindex="-1">
                            <div class="modal-dialog  modal-xl">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="exampleModalLabel">创建Bot</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                            aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <div class="mb-3">
                                            <label for="add-bot-title" class="form-label">名称</label>
                                            <input v-model="botadd.title" type="text" class="form-control"
                                                id="add-bot-title" placeholder="请输入Bot名称">
                                        </div>
                                        <div class="mb-3">
                                            <label for="add-bot-description" class="form-label">简介</label>
                                            <textarea v-model="botadd.description" class="form-control"
                                                id="add-bot-description" rows="3" placeholder="请输入Bot简介"></textarea>
                                        </div>
                                        <div class="mb-3">
                                            <label for="add-bot-code" class="form-label">代码</label>
                                            <VAceEditor v-model:value="botadd.content" @init="editorInit" lang="c_cpp"
                                                theme="textmate" style="height: 300px" />
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <div class="error-message">{{ botadd.message }}</div>
                                        <button type="button" class="btn btn-primary" @click="add_bot">创建</button>
                                        <button type="button" class="btn btn-secondary"
                                            data-bs-dismiss="modal">取消</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="card-body">
                        <table class="table table-striped table-hover">
                            <thead>
                                <tr>
                                    <th scope="col">名称</th>
                                    <th scope="col">创建时间</th>
                                    <th scope="col">操作</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr v-for="bot in bots" :key="bot.id">
                                    <td>{{ bot.title }}</td>
                                    <td>{{ bot.createtime }}</td>
                                    <td>
                                        <!-- Button trigger modal -->
                                        <button type="button" class="btn btn-secondary" data-bs-toggle="modal"
                                            style="margin-right: 10px" :data-bs-target="'#update-bot-btn-' + bot.id">
                                            修改
                                        </button>
                                        <button type="button" class="btn btn-danger"
                                            @click="remove_bot(bot)">删除</button>

                                        <!-- Modal -->
                                        <div class="modal fade " :id="'update-bot-btn-' + bot.id" tabindex="-1">
                                            <div class="modal-dialog  modal-xl">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h5 class="modal-title" id="exampleModalLabel">修改Bot</h5>
                                                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                            aria-label="Close"></button>
                                                    </div>
                                                    <div class="modal-body">
                                                        <div class="mb-3">
                                                            <label for="add-bot-title" class="form-label">名称</label>
                                                            <input v-model="bot.title" type="text" class="form-control"
                                                                id="add-bot-title" placeholder="请修改Bot名称">
                                                        </div>
                                                        <div class="mb-3">
                                                            <label for="add-bot-description"
                                                                class="form-label">简介</label>
                                                            <textarea v-model="bot.description" class="form-control"
                                                                id="add-bot-description" rows="3"
                                                                placeholder="请修改Bot简介"></textarea>
                                                        </div>
                                                        <div class="mb-3">
                                                            <label for="add-bot-code" class="form-label">代码</label>
                                                            <VAceEditor v-model:value="bot.content" @init="editorInit"
                                                                lang="c_cpp" theme="textmate" style="height: 300px" />
                                                        </div>
                                                    </div>
                                                    <div class="modal-footer">
                                                        <div class="error-message">{{ bot.message }}</div>
                                                        <button type="button" class="btn btn-primary"
                                                            @click="update_bot(bot)">保存</button>
                                                        <button type="button" class="btn btn-secondary"
                                                            data-bs-dismiss="modal">取消</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
import { useStore } from 'vuex'
import { ref } from 'vue'
// 在vue3中绑定对象使用reactive会更方便
import { reactive } from 'vue'
import $ from 'jquery'
import { Modal } from 'bootstrap/dist/js/bootstrap'
import { VAceEditor } from 'vue3-ace-editor';
import ace from 'ace-builds';
export default {
    components: {
        VAceEditor
    },
    setup() {
        ace.config.set(
            "basePath",
            "https://cdn.jsdelivr.net/npm/ace-builds@" + require('ace-builds').version + "/src-noconflict/")
        const store = useStore();
        let bots = ref([]);//bots用于保存getlist返回的bot列表
        let follows = reactive({
            follower_count: null, //关注数
            fan_count: null, //粉丝数
        })
        // 定义一个Bot对象
        const botadd = reactive({
            title: "",
            description: "",
            content: "",
            message: "",
        });

        //定义函数——刷新bot列表
        const refresh_bots = () => {
            $.ajax({
                url: "http://127.0.0.1:3000/user/bot/getlist/",
                type: "get",
                headers: {
                    Authorization: "Bearer " + store.state.user.token,
                },
                success(resp) {
                    bots.value = resp;
                }
            })
        }
        refresh_bots();//调用函数

        //定义函数——创建bot
        const add_bot = () => {
            botadd.message = "";
            $.ajax({
                url: "http://127.0.0.1:3000/user/bot/add/",
                type: "post",
                data: {
                    title: botadd.title,
                    description: botadd.description,
                    content: botadd.content,
                },
                headers: {
                    Authorization: "Bearer " + store.state.user.token,
                },
                success(resp) {
                    if (resp.message === "success") {
                        botadd.title = "";
                        botadd.description = "";
                        botadd.content = "";
                        Modal.getInstance("#add-bot-btn").hide();
                        refresh_bots();
                    } else {
                        botadd.message = resp.message;
                    }
                }
            })
        }

        //定义函数——删除Bot
        const remove_bot = (bot) => {
            $.ajax({
                url: "http://127.0.0.1:3000/user/bot/remove/",
                type: "post",
                headers: {
                    Authorization: "Bearer " + store.state.user.token,
                },
                data: {
                    bot_id: bot.id
                },
                success(resp) {
                    if (resp.message == "success") {
                        refresh_bots();
                    } else {
                        alert(resp.message)
                    }
                }
            })
        }
        //定义函数——修改bot
        const update_bot = (bot) => {
            bot.message = "";
            $.ajax({
                url: "http://127.0.0.1:3000/user/bot/update/",
                type: "post",
                data: {
                    bot_id: bot.id,
                    title: bot.title,
                    description: bot.description,
                    content: bot.content,
                },
                headers: {
                    Authorization: "Bearer " + store.state.user.token,
                },
                success(resp) {
                    if (resp.message === "success") {
                        Modal.getInstance('#update-bot-btn-' + bot.id).hide();
                        alert("修改成功");
                        refresh_bots();
                    } else {
                        bot.message = resp.message;
                    }
                }
            })
        }

        const getFollow = () => {
            $.ajax({
                url: "http://127.0.0.1:3000/ranklist/follow/getlist/",
                data: {
                    type: "2"
                },
                type: "get",
                headers: {
                    Authorization: "Bearer " + store.state.user.token,
                },
                success(resp) {
                    resp.forEach(item => {
                        if (item.followerId == store.state.user.id) {
                            follows.follower_count++;
                        }
                        if (item.followeeId == store.state.user.id) {
                            follows.fan_count++;
                        }
                    })
                    console.log(follows,"s")
                },
                error(resp) {
                    console.log(resp)
                }
            })
        }
        getFollow()
        return {
            bots,
            botadd,
            add_bot,
            remove_bot,
            update_bot,
            follows
        }

    }
}
</script>
<style scoped>
div.card {
    margin-top: 20px;
}

div.error-message {
    color: red;
}
</style>