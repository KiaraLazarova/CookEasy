let postCommentForm = document.querySelector('#postCommentForm');

postCommentForm.addEventListener('submit', event => {
    let commentContent = postCommentForm.querySelector('#content');

    if(commentContent.value.length >= 10) return alert('Hello :) Your comment is successfully received - it will be visible under the particular recipe after being approved by one of our administrators!');
});