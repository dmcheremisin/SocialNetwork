var file;
function dragAndDrop(event) {
    event.preventDefault();
    event.stopPropagation();
    var fileName = URL.createObjectURL(event.dataTransfer.files[0]);
    var $preview = $(".image-drop");
    if($("div.image-drop img").length > 0 ) {
        $("div.image-drop > img").remove();
    } else{
        $("div.image-drop > p").remove();
    }
    var previewImg = document.createElement("img");
    previewImg.setAttribute("class", "img-circle img-thumbnail image-upload uploaded-image");
    previewImg.setAttribute("src", fileName);
    $preview.append(previewImg);
    file = event.dataTransfer.files[0];
    dragLeave(event);
};
function dragEnter(event) {
    event.preventDefault();
    event.stopPropagation();
    var $div = $("div.image-drop");
    $div.removeClass("border-dotted");
    $div.addClass("border-dotted-big");
    return false;
};
function dragLeave(event) {
    event.preventDefault();
    event.stopPropagation();
    var $div = $("div.image-drop");
    $div.removeClass("border-dotted-big");
    $div.addClass("border-dotted");
    return false;
};
$("button.btn-left").click(function (event) {
    event.preventDefault();
    var url = './index.html';
    var formData = new FormData();
    console.log(file);
    formData.append("file", file);
    $.ajax({
        url: './index.html',
        type: 'POST',
        data: formData,
        processData: false,
        contentType: false,
        success: function(data){
            alert(data);
        }
    });
});