export default ({
    state: {
      is_record:false, 
      a_steps:"",
      b_steps:"",
      record_loser:"",
      baseMsg: {},
      a_username: null,
      a_photo: null,
      a_rating: null,
      a_content: null,
      b_username: null,
      b_photo: null,
      b_rating: null,
      b_content: null,
      round: 0,
      likeNum: null,
      commentList: [],
      comment_errorMsg: null,
    },
    mutations: {
      updateIsRecord(state, is_record){
        state.is_record = is_record;
      },
      updateLikeNum(state,likeNum) {
        state.likeNum = likeNum
      },
      updateSteps(state, data){
        state.a_steps = data.a_steps;
        state.b_steps = data.b_steps;
        state.round = data.a_steps.length;
      },
      updateRecordLoser(state, loser){
        state.record_loser = loser;
      },
      updateRecord(state, record){
        state.a_username = record.a_username;
        state.a_photo = record.a_photo;
        state.a_rating = record.a_rating;
        state.a_content = record.a_content;
        state.b_username = record.b_username;
        state.b_photo = record.b_photo;
        state.b_rating = record.b_rating;
        state.b_content = record.b_content;
      },
      updateCommentList(state,data) {
        state.commentList = data
      },
      updateCommentErrorMsg(state,data) {
        state.comment_errorMsg = data
      }
    },
    actions: {
       
    },
    modules: {
    }
  })
  