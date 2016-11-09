<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <spring:url value="resources/kontur.css" var="mainCss"/>

    <title>СКБ-контур | Тестовое задание</title>
    <link rel='stylesheet' href='resources/css/kontur.css'>
    <link rel='stylesheet' href='webjars/bootstrap/3.3.7/css/bootstrap.min.css'>
</head>
<body>
<div class="container-fluid">
    <form:form class="form-add-dictionary" method="post" enctype="multipart/form-data">

        <div class="form-group" id="dictInitBlock">
            <h3>Загрузите словарь</h3>
            <label class="btn btn-default btn-file" id="dictBtn">
                Выбрать файл словаря
                <input id="dictInput" type="file" name="file" style="display: none;"/>
            </label>


            <input id="sendInput" type="button" class="btn btn-default btn-primary disabled" type="button"
                   value="Отправить словарь">

        </div>

        <h3 style="display: none;" id="autocompleteHead">Вводите слова</h3>
        <div class="row">
            <div class="col-lg-12">
                <div class="input-group-sm">
                    <input type="text" id="autocomplete" name="autocomplete" class="form-control input-sm"
                           style="display: none;"
                           data-provide="typeahead">
                </div>
            </div>
        </div>


    </form:form>

    <script type="text/javascript" src="webjars/jquery/1.10.2/jquery.min.js"></script>
    <script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="resources/js/bootstrap-typeahead.js"></script>
    <script>
        $(document).ready(function () {
            var substringMatcher = function (strs) {
                return function findMatches(q, cb) {
                    var matches, substringRegex;

                    // an array that will be populated with substring matches
                    matches = [];

                    // regex used to determine if a string contains the substring `q`
                    substrRegex = new RegExp(q, 'i');

                    // iterate through the pool of strings and for any string that
                    // contains the substring `q`, add it to the `matches` array
                    $.each(strs, function (i, str) {
                        if (substrRegex.test(str)) {
                            matches.push(str);
                        }
                    });

                    cb(matches);
                };
            };

            $('#autocomplete').typeahead({
                highlighter: function (item) {
                    return item;
                },
                ajax: {
                    url: "doAutocomplete",
                    timeout: 500,
                    triggerLength: 1,
                    method: "get"
                }
            });

            $('#sendInput').prop('disabled', true);

            $('#sendInput').click(function (event) {
                var formData = new FormData();
                formData.append("data", $("#dictInput")[0].files[0]);
                $.ajax({
                    type: "POST",
                    processData: false,
                    contentType: false,
                    url: "kontur",
                    data: formData
                }).done(function () {
                    $('#dictInitBlock').remove();
                    $("#autocomplete").removeAttr("style");
                    $("#autocompleteHead").removeAttr("style");
                });

            });


        });
        $("#dictInput").change(function () {
            var sendInput = $("#sendInput");
            sendInput.prop('disabled', false);
            sendInput.removeClass("disabled");
        });

    </script>

</div>

</body>
</html>
