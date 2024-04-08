<template>
    <div>
        <PlayGround v-if="$store.state.pk.status === 'playing'" />
        <MatchGround v-if="$store.state.pk.status === 'matching'" />
        <ResultBoard v-if="$store.state.pk.loser != 'none'" />
    </div>
</template>
<script>
import { onMounted, onUnmounted } from 'vue';
import PlayGround from '../../components/PlayGround.vue'
import MatchGround from '../../components/MatchGround.vue'
import ResultBoard from '../../components/ResultBoard.vue'
import { useStore } from 'vuex';
export default {
    components: {
        PlayGround,
        MatchGround,
        ResultBoard
    },
    setup() {
        const store = useStore()
        const socketUrl = `ws://127.0.0.1:3000/websocket/${store.state.user.token}/`;
        let socket = null;
        
        store.commit("updateLoser", "none");//先更新为none 因为之前的对比结果会保存下来
        onMounted(() => {
            store.commit("updateOpponent", {
                username: "我的对手",
                photo: "https://cdn.acwing.com/media/article/image/2022/08/09/1_1db2488f17-anonymous.png",
            });
            socket = new WebSocket(socketUrl);

            socket.onopen = () => {
                store.commit("updateSocket",socket)
            }

            socket.onmessage = msg => {
                const data = JSON.parse(msg.data);
                if (data.event === "start-matching") {
                    store.commit("updateOpponent", {
                        username: data.opponent_username,
                        photo: data.opponent_photo
                    });
                    setTimeout(() => {
                        store.commit("updateStatus", "playing")
                    }, 200);
                    store.commit("updateGame", data.game)
                }else if (data.event === "move") {
                    const game = store.state.pk.gameObject;
                    const [snack0, snack1] = game.snakes;
                    snack0.set_direction(data.a_direction);
                    snack1.set_direction(data.b_direction);
                } else if (data.event === "result") {
                    const game = store.state.pk.gameObject;
                    const [snack0, snack1] = game.snakes;
                    if (data.loser === "all" || data.loser === "A") {
                        snack0.status = "die";
                    }
                    if (data.loser === "all" || data.loser === "B") {
                        snack1.status = "die";
                    }
                    store.commit("updateLoser", data.loser);
                }
            }

            socket.onclose = () => {
            }
        })

        onUnmounted(() => {
            socket.close();
            store.commit("updateStatus", "matching")
        })
    }
}
</script>
<style scoped>
div.user-color {
    text-align: center;
    color: orange;
    font-size: 30px;
    font-weight: 600;
}
</style>