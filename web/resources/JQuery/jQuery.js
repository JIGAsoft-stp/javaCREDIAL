$(document).ready(function () {
    $(".calendar").datepicker({
        
        dayNames: ['Domingo', 'Segunda', 'Terça', 'Quarta', 'Quinta', 'Sexta', 'Sábado', 'Domingo'],
        dayNamesMin: ['D', 'S', 'T', 'Q', 'Q', 'S', 'S', 'D'],
        dayNamesShort: ['Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sáb', 'Dom'],
        monthNames: ['Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio', 'Junho', 'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro', 'Dezembro'],
        monthNamesShort: ['Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez'],
        changeMonth: true,
        changeYear: true,
        dateFormat : "dd-mm-yy"

    });

    $('.bodyPrevNext div').css("display", "none");
    $('.can').css("display", "block");
    $('.cont').css("display", "block");
    $("a").click(function () {
        if ($(this).attr('href') === '#um') {
            $('.bodyPrevNext div').css("display", "none");
            $('.can').css("display", "block");
            $('.cont').css("display", "block");
        } else if ($(this).attr('href') === '#dois') {
            $('.bodyPrevNext div').css("display", "none");
            $('.ant1').css("display", "block");
            $('.next').css("display", "block");
        } else if ($(this).attr('href') === '#tres') {
            $('.bodyPrevNext div').css("display", "none");
            $('.ant2').css("display", "block");
            $('.conc').css("display", "block");
        }
    });
    $(".cont").click(function () {
        $("#ID_accordion2").show($("#ID_accordion2").fadeIn(800));
    });
    $(".next").click(function () {
        $("#ID_accordion3").show($("#ID_accordion3").fadeIn(800));
    });

});
