$(document).ready(function (e)
{
    $(".botaoSalvar").click(function (e)
    {
         e.preventDefault();
         var senhaAtual = $(".senhaAtualLogin").val();
         var novaSenha = $(".NovaSenhaLogin").val();
         var confirmarSenha = $(".ConfirmarSenhaLogin").val();
         if(senhaAtual ==="")
             $(".senhaAtualLogin").css("border","1px solid red");
         else
             $(".senhaAtualLogin").css("border","");
         if(novaSenha ==="")
             $(".NovaSenhaLogin").css("border","1px solid red");
         else
             $(".NovaSenhaLogin").css("border","");
         if(confirmarSenha ==="")
             $(".ConfirmarSenhaLogin").css("border","1px solid red");
         else
              $(".ConfirmarSenhaLogin").css("border","");
    });

   
});

