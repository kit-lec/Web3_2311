$(function(){
    // 글 [삭제] 버튼
    $("#btnDel").click(function(){
        let answer = confirm("삭제하시겠습니까?");
        if(answer){
            $("form[name='frmDelete']").submit();
        }
    });

    // 현재 글의 id 값
    const id = $("input[name='id']").val().trim();

    // 현재 글의 댓글을 불러온다
    loadComment(id);

});

// 특정 글 (post_id) 의 댓글 목록 읽어오기
function loadComment(post_id){
    $.ajax({
        url: "/comment/list?id=" + post_id,
        type: "GET",
        cache: false,
        success: function(data, status){
            if(status == "success"){
                // 서버측 에러 메세지가 있는 경우.
                if(data.status !== "OK"){
                    alert(data.status);
                    return;
                }

                buildComment(data);   // 댓글 화면 렌더링

                // TODO
            }
        },
    });
}

function buildComment(result){
    $("#cmt_cnt").text(result.count);   // 댓글 총 개수
    const out = [];

    result.data.forEach(comment => {
        let id = comment.id;
        let content = comment.content.trim();
        let regdate = comment.regdate;
        let user_id = parseInt(comment.user.id);
        let username = comment.user.username;
        let name = comment.user.name;

        const delBtn = `<i class="btn fa-solid fa-delete-left text-danger" data-bs-toggle="tooltip" title="삭제"></i>`;
        const row = `
            <tr>
                <td><span><strong>USER1</strong><br><small class="text-secondary">(사용자1)</small></span></td>
                <td>
                    <span>정말 정말 재미 있습니다.</span>
                </td>
                <td><span><small class="text-secondary">2022.03.22 13:43:05</small></span></td>
            </tr>
            `;
    });

}


