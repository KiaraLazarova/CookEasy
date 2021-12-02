let recipeId = document.querySelector('#recipeId').value;
let commentsDiv = document.querySelector('#commentsDiv');
let comments = [];

fetch(`http://localhost:8080/api/${recipeId}/comments`).
then(response => response.json()).
then(data => {
    data.forEach(comment => comments.push(comment));
    displayAllComments(comments);
});

function displayAllComments(comments) {
    commentsDiv.innerHTML = comments.map(comment => displaySingleComment(comment)).join('');
}

function displaySingleComment(comment) {
    return `<div class="row mb-4">
                  <div class="col-2">
                      <img src="/images/${comment.authorGenderNameEnumName}_profile_avatar.png"
                           class="img-fluid rounded" style="width: 100px; border-color: white;"  alt=""/>
                  </div>

                  <div class="col-9">
                      <p class="mb-2"><strong>@${comment.authorUsername}</strong></p>
                      <span class="font-weight-light text-info mb-2">Posted on <u>${comment.createdOn}</u></span>
                      <p class="text-center">${comment.content}</p>
                  </div>
              </div>`;
}