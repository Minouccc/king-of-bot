<!-- 基本组件:游玩区域 -->

<template>
    <div class="playground">
        <div style="margin-right: 50px;">
            <div class="title">{{ title1 }}</div>
            <div class="user_photo">
                <img :src="$store.state.pk.a_photo" alt="">
            </div>
            <div class="user_username">
                {{ $store.state.pk.a_username }}
            </div>
            <div class="parallelogram1" :class="{ check: isMe1 }"></div>
        </div>
        <GameMap />
        <div style="margin-left: 50px;">
            <div class="title">{{ title2 }}</div>
            <div class="user_photo">
                <img :src="$store.state.pk.b_photo" alt="">
            </div>
            <div class="user_username">
                {{ $store.state.pk.b_username }}
            </div>
            <div class="parallelogram2" :class="{ check: isMe2 }"></div>
        </div>
    </div>
</template>

<script>
import GameMap from './GameMap.vue'
import { useStore } from 'vuex';
import { computed } from 'vue'
export default {
    components: {
        GameMap
    },
    setup() {
        const store = useStore()
        const title1 = computed(()=>{
            return store.state.pk.a_username === store.state.pk.opponent_username ? '你的对手' : '我'
        })
        const title2 = computed(()=>{
            return store.state.pk.b_username === store.state.pk.opponent_username ? '你的对手' : '我'
        })
        const isMe1 = computed(() => {
            return title1.value === '我';
        })
        const isMe2 = computed(() => {
            return title2.value === '我';
        })
        return {
            title1,
            title2,
            isMe1,
            isMe2
        }
    }
}
</script>

<style scoped>
div.playground {
    display: flex;
    /* 浏览器宽度60% */
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
}

.title {
    text-align: center;
    font-size: 24px;
    font-weight: 600;
    color: #333;
}

div.user_username {
    text-align: center;
    font-size: 24px;
    font-weight: 600;
    color: white;
    padding-top: 2vh;
}
.parallelogram1 {
  margin: 20px auto;
  width: 100px;
  height: 50px;
  background-color: #4876EC;
  transform: skew(20deg);
}
.check::before, .check::after {
  content: '';
  position: absolute;
  top: 0px;
  left: 40px;
  width: 20px;
  height: 40px;
  background-color: transparent;
  border-bottom: 2px solid white;
  border-right: 2px solid white;
  transform: rotate(45deg);
}
.parallelogram2 {
  margin: 20px auto;
  width: 100px;
  height: 50px;
  background-color: #F94848;
  transform: skew(-20deg);
}
</style>
