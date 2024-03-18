const deleteButton = document.getElementById('delete-btn');
const modifyButton = document.getElementById('modify-btn');
const createButton = document.getElementById('create-btn');

if(deleteButton){
    deleteButton.addEventListener('click',event=>{
        let id = document.getElementById("article-id").value;
        fetch(`/articles/${id}`,{method: 'DELETE'})
            .then(()=>{alert('삭제가 완료되었습니다.');
                            location.replace('/articles');});
    });
}

if(modifyButton){
    modifyButton.addEventListener('click',event=>{

       let title = document.getElementById('title-input');
       let content = document.getElementById('content-input');
        // let params= new URLSearchParams(location.search);
        // let id=params.get('id');
        let id = document.getElementById('article-id').value;
       fetch(`/articles/${id}`,{
           method:'PUT',
           headers:{"Content-Type":"application/json"},
           body: JSON.stringify({
               "title":title.value,
               "content":content.value
           }
           )}).then(()=>{alert('수정이 완료되었습니다.');
                                location.replace(`/article/${id}`);});
    });
}

if(createButton){
    createButton.addEventListener('click',event=>{
        let title = document.getElementById('title-input').value;
        let content = document.getElementById('content-input').value;
        fetch(`/articles/insert`,{
            method:'POST',
            headers:{'Content-Type':'application/json'},
            body: JSON.stringify({
                "title":title,
                "content":content
            })
        }).then(()=>{alert('등록이 완료되었습니다.');location.replace('/articles');});
        location.replace('/articles');
    });

}

const updateButton = document.querySelectorAll('.update-comment');
const inputelement = document.querySelectorAll('.change-comment');
const updateRealButton = document.querySelectorAll('.update-comment-execute');
if(updateButton) {
    updateButton.forEach((element,index) => {
        console.log(element);
        element.addEventListener('click',()=>{
            inputelement[index].style.cssText = 'visibility:visible';
            updateRealButton[index].style.cssText = 'visibility:visible';
            updateButton[index].style.cssText = 'visibility:hidden';
        });
    });
}
if(updateRealButton) {
    updateRealButton.forEach( (element,index) => {
        element.addEventListener('click',()=>{
            let string = inputelement[index].value;
            // let params= new URLSearchParams(location.search);
            // let id=params.get('id');
            let id = document.getElementById('article-id').value;
            let comment_id = document.querySelectorAll('.comment-id')[index].value;
            console.log(id);
            console.log(string);
            fetch('/comment', {
                method: 'PUT',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify({
                    "id": comment_id,
                    "comment": string,
                    "article_id": id
                })
            }).then(() => location.replace(`./${id}`));
            inputelement[index].style.cssText = 'visibility:hidden';
            updateRealButton[index].style.cssText = 'visibility:hidden';
            updateButton[index].style.cssText = 'visibility:visible';
        });

    });
}
const deleteCommentButton = document.querySelectorAll('.delete-comment');
if(deleteCommentButton) {
    deleteCommentButton.forEach( (element,index) => {
        element.addEventListener('click',()=>{
            let id = document.querySelectorAll('.comment-id')[index].value;
            let article_id=document.getElementById('article-id').value;
            fetch(`/comment/${id}`, {method: 'DELETE'}).then(() => location.replace(`./${article_id}`));
        });

    });
}
const postCommentButton = document.querySelector('#post-comment');
postCommentButton.addEventListener('click',()=>{
    let id = document.getElementById('article-id').value;
    let comment = document.getElementById('comment-input').value;
   fetch('/comment',{method:'POST',
                                headers:{'Content-Type':'application/json'},
                                body:JSON.stringify({
                                    "comment":comment,
                                    "article_id":id
                                })
   }).then(()=>location.replace(`./${id}`));
});
