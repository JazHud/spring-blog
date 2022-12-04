$(document).ready(function (){
    $(".editButton").on('click', function(){
        console.log($(this).attr("data-id"));
        window.location.replace(`/posts/${$(this).attr("data-id")}/edit`)
        // $.get(`/posts/${$(this).attr("data-id")}`);
    });
});