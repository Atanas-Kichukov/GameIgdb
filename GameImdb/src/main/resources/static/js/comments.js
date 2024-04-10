const gameId = document.getElementById('gameId').value

commentsCtnr = document.getElementById('commentCntr')

const allComments = []

const displayComments = (comments) =>{
commentsCtnr.innerHTML = comments.map((c) =>{return asComment(c)}.join('')
}


function asComment(c){

    let commentHtml = '<div id = "commentCntr-${c.commentId}">'
    commentHtml += '<h4>${c.user} (${c.created})</h4>'
    commentHtml += '<p>${c.message}</p>'
    commentHtml += '</div>'

    return commentHtml
}

fetch(`http://localhost:8080/api/${gameId}/comments`)
    .then(response => response.json())
    .then(info => {
        for (let comment of info) {
            allComments.push(comment)
        }

        displayComments(allComments)
    });