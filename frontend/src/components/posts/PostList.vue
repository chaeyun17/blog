<template>
<div>
  <h2>글 목록</h2>
  <b-row>
    <b-col></b-col>
    <b-col cols="2" class="text-right">
      <b-button type="button" variant="primary" @click="goCreate">새 글</b-button>
    </b-col>
  </b-row>
  
  <div class="post-list">
    <div>
      <b-table striped hover :items="posts" 
      @row-clicked="rowClickHanlder"
      ></b-table>
    </div>
  </div>
</div>

</template>

<script>

export default {
  name: 'PostList',
  props: {
  },
  data(){
    return {
      posts: []
    }
  },
  mounted(){
    this.fetchPosts();
  },
  methods: {
    fetchPosts(){
      this.$http.get('/posts')
      .then(res=>{
        this.posts = res.data.content.map(post=>{
          return {
            No: post.id,
            제목: post.title,
            작성일: post.createdOn,
            업데이트일: post.updatedOn
          }
        });
      });
    },
    goCreate(){
      this.$router.push('/posts/create');
    },
    rowClickHanlder(row){
      console.log(row);
      this.$router.push(`/posts/${row.No}`);
    }
  }
}
</script>

<style scoped>
  .text-right{
    text-align: right;
  }
</style>