var uploadFiles = [];
var challenge = {
  init: function () {
    var _this = this;
    $("#btn-save").on("click", function ()  {
      _this.save();
    });

    $("#btn-update").on("click", function () {
      _this.update();
    })

    $("#btn-delete").on("click", function () {
      _this.delete();
    })

    $(document).ready(function () {
            var reader = new FileReader();
            var fileList = [];
            delImg = function(_this) {
              var parent = $(_this).parent("li");
              var index = parent.index();
              console.log(uploadFiles);
              console.log(index);
              console.log(fileList);
              uploadFiles.splice(index, 1);
              fileList.splice(index, 1);
              console.log(uploadFiles);
              console.log(fileList);

              parent.remove();
            }
            $("#fileInput").on("change", function(e) {
              var filename = e.target.files[0].name;
              var file = e.target.files[0];
              reader.onload = function (e) {

                var li = document.createElement("li");
                var img = document.createElement("img");
                var span = document.createElement("span");
                li.setAttribute("style", " float: left; position: relative; list-style-type: none;");
                img.setAttribute("src", e.target.result);
                img.setAttribute("style", "width: 150px; height: 150px;");
                span.setAttribute("id", "btn-delete-img");
                span.setAttribute("style", "position: absolute; top: 0; right: 0; font-size: 13px; background-color: #000; color: #fff; width: 18px; height:18px; line-height: 16px; display: inline-block; text-align:center;");
                span.setAttribute("onclick", "delImg(this)");
                span.innerHTML += "x";
                li.appendChild(img);
                li.appendChild(span);
                document.querySelector("ul#preview").appendChild(li);
                uploadFiles.push(file);
                fileList.push(filename);
              };
              reader.readAsDataURL(file);
              $("#userfile").val(fileList.join(', '));
            });
          });
    },
  save: function () {
      var form = new FormData($("#input-form")[0]);
      for(var file of uploadFiles) {
        form.append('img', file, file.name);
      }

      for(var value of form.entries()) {console.log(value)}
      $.ajax({
        type: "POST",
        url: "http://localhost:8080/api/challenge",
        dataType: "json",
        data: form,
        contentType: false,
        processData: false
      }).done(() => {
        alert("등록되었습니다");
        window.location.href = "/api/admin/challenge";
      }).fail(function (error) {
         alert(JSON.stringify(error));
      });
    },

  update: function () {
    var form = new FormData($("#input-form")[0]);
    var path = location.pathname.split("/");
    var id = path[path.length - 1];
    for(var file of uploadFiles) {
      form.append('img', file, file.name);
    }
    for(var value of form.entries()) {console.log(value)}
          $.ajax({
            type: "PUT",
            url: `http://localhost:8080/api/challenge/${id}`,
            dataType: "json",
            data: form,
            contentType: false,
            processData: false
          }).done(() => {
            alert("수정되었습니다");
            window.location.href = "/api/admin/challenge";
          }).fail(function (error) {
             alert(JSON.stringify(error));
          });
  },

  delete: function () {
    var path = location.pathname.split("/");
    var id = path[path.length - 1];
    $.ajax({
      type: "DELETE",
      url: `http://localhost:8080/api/challenge/${id}`,
      dataType: "json",
      contentType: "aplictaion/json; charset=utf-8"
    }).done(function() {
      alert('글이 삭제되었습니다');
      window.location.href = '/api/admin/challenge'
    }).fail(function (error) {
      alert(JSON.stringify(error));
    })
  },

};

challenge.init();
