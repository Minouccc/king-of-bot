<!-- 基本组件:回放区域 -->

<template>
    <div class="playground" :key="refreshKey">
        <div class="container text-center">
            <div class="row">
                <div class="col-2">
                    <div class="user_photo">
                        <img :src="$store.state.record.a_photo" alt="">
                    </div>
                    <div class="user_username">
                        {{ $store.state.record.a_username }}
                    </div>
                    <div class="rating">
                        天梯积分{{ $store.state.record.a_rating }}
                    </div>
                    <div class="progress-container">
                        <div class="progress-bar1" :style="{ width: `${progressBarWidth1}px` }"></div>
                    </div>
                    <button v-if="$store.state.record.a_content !== '-1'" type="button" class="btn btn-light"
                        data-bs-toggle="modal" data-bs-target="#exampleModal1">
                        <img src="../assets/images/view.png" alt="" style="width: 30px;height: 30px;">
                        <span>查看</span>
                    </button>
                    <div class="bot" v-if="$store.state.record.a_content === '-1'">亲自上阵</div>
                </div>
                <div class="col-8">
                    <GameMap />
                </div>
                <div class="col-2">
                    <div class="user_photo">
                        <img :src="$store.state.record.b_photo" alt="">
                    </div>
                    <div class="user_username">
                        {{ $store.state.record.b_username }}
                    </div>
                    <div class="rating">
                        天梯积分{{ $store.state.record.b_rating }}
                    </div>
                    <div class="progress-container">
                        <div class="progress-bar2" :style="{ width: `${progressBarWidth2}px` }"></div>
                    </div>
                    <button v-if="$store.state.record.b_content !== '-1'" type="button" class="btn btn-light"
                        data-bs-toggle="modal" data-bs-target="#exampleModal2">
                        <img src="../assets/images/view.png" alt="" style="width: 30px;height: 30px;">
                        <span>查看</span>
                    </button>
                    <div class="bot" v-if="$store.state.record.b_content === '-1'">亲自上阵</div>
                </div>
            </div>
        </div>
        <div class="progress-container-under">
            <div class="progress-bar" :style="{ width: `${progressBarWidth3}px` }">
                当前回合：{{ currentRound }}
            </div>
            <img src="../assets/images/refresh.png" alt="" class="refresh" title="重新回放" @click="refresh">
            <div style="display: flex;">
                <img src="../assets/images/like.png" alt="" class="refresh" title="点赞" @click="addLike">
                <span>{{ $store.state.record.likeNum }}</span>
            </div>
        </div>
        <div class="card">
            <div class="card-header d-flex align-items-center justify-content-between">
                <span style="font-size:16px">讨论区</span>
                <!-- Button trigger modal -->
                <button type="button" class="btn btn-primary btn-sm" data-bs-toggle="modal"
                    data-bs-target="#add-post-btn">
                    发表
                </button>
                <!-- Modal -->
                <div class="modal fade " id="add-post-btn" tabindex="-1">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h1 class="modal-title fs-5" id="exampleModalLabel">发表评论</h1>
                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                    aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <form>
                                    <div class="mb-3">
                                        <label for="message-text" class="col-form-label">Comment:</label>
                                        <textarea v-model="content" class="form-control" id="message-text"
                                            placeholder="说点什么..."></textarea>
                                    </div>
                                </form>
                            </div>
                            <div class="modal-footer">
                                <div class="error-message">{{ $store.state.record.comment_errorMsg }}</div>
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">关闭</button>
                                <button type="button" class="btn btn-primary" @click="confirmPost">确认发表</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="card-body">
                <template v-for="comment in $store.state.record.commentList" :key="comment.id">
                    <div class="comment-wrapper">
                        <div class="comment-img">
                            <img :src="comment.photo" alt="">
                        </div>
                        <div class="comment-content">
                            <div class="poster-name">{{ comment.name }}</div>
                            <div class="poster-time">发布于：{{ comment.createtime }}</div>
                            <div class="poster-content">
                                {{ comment.content }}
                            </div>
                        </div>
                    </div>
                </template>
            </div>
        </div>
    </div>

    <div class="modal fade" id="exampleModal1" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="exampleModalLabel">Bot代码查看</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <VAceEditor :value="$store.state.record.a_content" @init="editorInit" lang="c_cpp" theme="textmate"
                        style="height: 300px" />
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">关闭</button>
                </div>
            </div>
        </div>
    </div>
    <div class="modal fade" id="exampleModal2" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="exampleModalLabel">Bot代码查看</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <VAceEditor :value="$store.state.record.b_content" @init="editorInit" lang="c_cpp" theme="textmate"
                        style="height: 300px" />
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">关闭</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import $ from 'jquery'
import { onMounted, computed, ref, onUnmounted } from 'vue';
import { useStore } from 'vuex';
import { VAceEditor } from 'vue3-ace-editor';
import { useRoute } from 'vue-router';
import GameMap from './GameMap.vue'
import { Modal } from 'bootstrap/dist/js/bootstrap'
export default {
    components: {
        GameMap,
        VAceEditor
    },
    setup() {
        let timer;
        const store = useStore();
        const route = useRoute();
        const recordId = route.params.recordId
        const currentRound = ref(0);
        const refreshKey = ref(0);
        const content = ref("");
        let comment_message = ref("")
        const roundDuration = store.state.record.round; // 回合数
        const refresh = () => {
            currentRound.value = 0;
            refreshKey.value++;
            startRoundTimer();
        }
        const addLike = () => {
            $.ajax({
                url: "http://127.0.0.1:3000/record/addLikeNum/",
                data: {
                    recordId,
                },
                type: "get",
                headers: {
                    Authorization: "Bearer " + store.state.user.token,
                },
                success(resp) {
                    if (resp.message === 'success') {
                        getLike();
                    }
                },
                error(resp) {
                    console.log(resp);
                }
            })
        }
        const getLike = () => {
            $.ajax({
                url: "http://127.0.0.1:3000/record/getLikeNum/",
                data: {
                    recordId,
                },
                type: "get",
                headers: {
                    Authorization: "Bearer " + store.state.user.token,
                },
                success(resp) {
                    store.commit("updateLikeNum", Number(resp.likeNum))
                },
                error(resp) {
                    console.log(resp);
                }
            })
        }
        const getCommentList = () => {
            $.ajax({
                url: "http://127.0.0.1:3000/record/comment/getlist",
                data: {
                    recordId,
                },
                type: "get",
                headers: {
                    Authorization: "Bearer " + store.state.user.token,
                },
                success(resp) {
                    store.commit("updateCommentList", resp)
                },
                error(resp) {
                    console.log(resp);
                }
            })
        }
        getCommentList()
        const confirmPost = () => {
            store.commit("updateCommentErrorMsg", "")
            $.ajax({
                url: "http://127.0.0.1:3000/record/comment/add/",
                type: "post",
                data: {
                    recordId: recordId,
                    content: content.value,
                },
                headers: {
                    Authorization: "Bearer " + store.state.user.token,
                },
                success(resp) {
                    if (resp.message === "success") {
                        content.value = "";
                        Modal.getInstance("#add-post-btn").hide();
                        getCommentList();
                    } else {
                        store.commit("updateCommentErrorMsg", resp.message);
                    }
                }
            })
        }
        const progressBarWidth1 = computed(() => {
            return ((Number(store.state.record.a_rating)) / 15);
        })
        const progressBarWidth2 = computed(() => {
            return ((Number(store.state.record.b_rating)) / 15);
        })
        const progressBarWidth3 = computed(() => {
            return `${(currentRound.value) * Math.floor((Math.floor(document.querySelector('.playground')?.offsetWidth) / roundDuration))}` - 7;
        });

        const updateProgress = () => {
            `${(currentRound.value) * Math.floor((Math.floor(document.querySelector('.playground')?.offsetWidth) / roundDuration))}` - 7;
        };

        const startRoundTimer = () => {
            timer = setInterval(() => {
                currentRound.value++;
                if (currentRound.value >= roundDuration) {
                    clearInterval(timer);
                }
                updateProgress();
            }, 1000);
        };

        onMounted(() => {
            startRoundTimer();
        });

        onUnmounted(() => {
            clearInterval(timer);
            store.commit("updateCommentErrorMsg", "")
        });


        return {
            progressBarWidth1,
            progressBarWidth2,
            progressBarWidth3,
            content,
            comment_message,
            currentRound,
            refresh,
            addLike,
            getLike,
            refreshKey,
            confirmPost,
            getCommentList
        }
    }
}
</script>

<style scoped>
div.playground {
    width: 80vw;
    /* 浏览器高度70% */
    height: 70vh;
    /* 居中 距离上方边距40px */
    margin: 40px auto;
}

div.user_photo {
    text-align: center;
    padding-top: 1vh;
}

div.user_photo>img {
    border-radius: 50%;
    width: 20vh;
    height: 20vh;
}

div.user_username {
    text-align: center;
    font-size: 24px;
    font-weight: 600;
    color: white;
    padding-top: 2vh;
}

div.rating {
    text-align: center;
    font-size: 16px;
    font-weight: 600;
    color: #333;
    padding-top: 2vh;
}

.refresh {
    box-sizing: border-box;
    width: 30px;
    height: 30px;
    cursor: pointer;
    margin-right: 2px;
}

.progress-container-under {
    display: flex;
    align-items: center;
    width: 100%;
    background-color: #f0f0f0;
    border-radius: 5px;
    margin: 60px auto;
    padding: 5px;
}

.progress-container {
    width: 100%;
    background-color: #f0f0f0;
    border-radius: 5px;
    margin: 20px auto;
    padding: 5px;
}

.progress-bar {
    height: 40px;
    background-color: #b47226;
    border-radius: 5px;
}

.progress-bar1 {
    height: 10px;
    background-color: #4876EC;
    border-radius: 5px;
}

.progress-bar2 {
    height: 10px;
    background-color: #F94848;
    border-radius: 5px;
}

div.bot {
    text-align: center;
    font-size: 24px;
    font-weight: 600;
    color: #333;
    padding-top: 2vh;
}

.comment-wrapper {
    display: flex;
    margin-bottom: 15px;
}

.comment-img {
    text-align: center;
    margin-right: 20px;
}

.comment-img>img {
    width: 50px;
    height: 50px;
    border-radius: 50%;
}

.comment-content {
    flex: 1;
}

.poster-name {
    font-size: 14px;
    color: #333;
}

.poster-time {
    font-size: 12px;
    color: #949494;
}

div.error-message {
    color: red;
}
</style>
