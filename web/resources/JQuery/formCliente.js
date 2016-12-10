/* global PF */

$(document).ready(function(e){
  var e;
   $(".editarSenha").click(function (e)
    {
       e.preventDefault();
       alterarSenha();
    });

     $(".novaSenha").blur(function (e)
    {
       e.preventDefault();
       var novaSenha = $(".novaSenha").val();
       if(novaSenha!=="")
           $(".novaSenha").css("border","1px solid green");
    });
    $(".cancelar").click(function(e){
        e.preventDefault();
        $(".modal").hide($(".modal").fadeOut(400)); 
      });
    $(".mensagemEnviar").click(function (e)
    {
       e.preventDefault();
       var nomeMensagem = $(".mensagemNome").val();
       var email = $(".mensagemEmail").val();
       var assunto = $(".mensagemAssunto").val();
       var info = $(".mensagemInfo").val();
       if(nomeMensagem ==="")
           $(".mensagemNome").css("border","1px solid red");
        else
            $(".mensagemNome").css("border","");
        if(email ==="")
            $(".mensagemEmail").css("border","1px solid red");
        else
            $(".mensagemEmail").css("border","");
        if(assunto ==="")
            $(".mensagemAssunto").css("border","1px solid red");
        else
            $(".mensagemAssunto").css("border","");
        if(info ==="")
            $(".mensagemInfo").css("border","1px solid red");
        else 
            $(".mensagemInfo").css("border","");
    });
  
 $(".clienteNif").blur(function (e)
{
    e.preventDefault();
   
  
});
    $(".n").keyup(function(e){
        e.preventDefault();
        var expre = /[^0-9]/g;
        // REMOVE OS CARACTERES DA EXPRESSAO ACIMA
        if ($(this).val().match(expre))
            $(this).val($(this).val().replace(expre,''));
   });
      $(".pagamentoNumero").keyup(function(e){
        e.preventDefault();
        var expre = /[^0-9]/g;
        // REMOVE OS CARACTERES DA EXPRESSAO ACIMA
        if ($(this).val().match(expre))
            $(this).val($(this).val().replace(expre,''));
   });
     $(".clienteMovel").keyup(function(e){
        e.preventDefault();
        var expre = /[^0-9]/g;
        // REMOVE OS CARACTERES DA EXPRESSAO ACIMA
        if ($(this).val().match(expre))
            $(this).val($(this).val().replace(expre,''));
   });
   
   $(".fechar").click(function (e){
         e.preventDefault();
         $(".modalChangePass").hide($(".modalChangePass").fadeOut(400));
         $(".senhaAtual").val("");
         $(".novaSenha").val("");
         $(".confSenha").val("");
         $(".senhaAtual").css("border","");
         $(".novaSenha").css("border","");
         $(".confSenha").css("border","");
   });
   $(".alterarSenhaUtilizador").click(function (e)
   {
        $(".modalChangePass").show($(".modalChangePass").slideDown(400));
   });
   $(".closeModal").click(function (e)
   {
        $(".modalChangePass").hide($(".modalChangePass").fadeOut(400));
         $(".senhaAtual").val("");
         $(".novaSenha").val("");
         $(".confSenha").val("");
         $(".senhaAtual").css("border","");
         $(".novaSenha").css("border","");
         $(".confSenha").css("border","");
   });
     $(".clienteFixo").keyup(function(e){
        e.preventDefault();
        var expre = /[^0-9]/g;
        // REMOVE OS CARACTERES DA EXPRESSAO ACIMA
        if ($(this).val().match(expre))
            $(this).val($(this).val().replace(expre,''));
   });
       $(".clienteNif").keyup(function(e){
        e.preventDefault();
        var expre = /[^0-9]/g;
        // REMOVE OS CARACTERES DA EXPRESSAO ACIMA
        if ($(this).val().match(expre))
            $(this).val($(this).val().replace(expre,''));
   });
       $(".tres1").keyup(function(e){
        e.preventDefault();
        var expre = /[^0-9]/g;
        // REMOVE OS CARACTERES DA EXPRESSAO ACIMA
        if ($(this).val().match(expre))
            $(this).val($(this).val().replace(expre,''));
   });
        $(".quatro").keyup(function(e){
        e.preventDefault();
        var expre = /[^0-9]/g;
        // REMOVE OS CARACTERES DA EXPRESSAO ACIMA
        if ($(this).val().match(expre))
            $(this).val($(this).val().replace(expre,''));
   });
   $(".clienteGuardar").click(function (e)
   {
      
        Validar(); 
   });
   
   $('.minus, .show-form-cl').click( function (){
       $('.sectionLeft').toggleClass('shrink');
   });
  
});

function Validar()
{
    var nif = $(".clienteNif").val();
    var nome = $(".clienteNome").val();
    var apelido = $(".clienteApelido").val();
    var sexo = $(".clienteSexo").val();
//   var dataNasc = "input:text[name='forma:dataN_input']";
    var estado = $(".clienteEstado").val();
    var morada = $(".clienteMorada").val();
    var prof = $(".clienteProf").val();
    var salario = $(".clienteSalario").val();
    var localidade = $(".clienteLocalidade").val();
    var localT = $(".clienteLocalT").val();
    var movel = $(".clienteMovel").val();
    var ano = $(".clienteAno").val();
    var mes = $(".clienteMes").val();
    var letra = $(".clienteLetra").val();
    var capa = $(".clienteCapa").val();
//    var email = $(".clienteEmail").val();
    
    if(nif ==='')
    {
        $(".clienteNif").css("border","1px solid red");
    }
    else
    {
         $(".clienteNif").css("border","");
    }
      if(nome ==='')
    {
        $(".clienteNome").css("border","1px solid red");
    }
    else
    {
         $(".clienteNome").css("border","");
    }
    if(apelido ==='')
    {
        $(".clienteApelido").css("border","1px solid red");
    }
    else
    {
         $(".clienteApelido").css("border","");
    }
      if(sexo ==='')
    {
        $(".clienteSexo").css("border","1px solid red");
    }
    else
    {
         $(".clienteSexo").css("border","");
    }
//    if($(dataNasc).val() ==="")
//        $(dataNasc).css("border","1px solid red");
//    else
//         $(dataNasc).css("border","");
    if(estado ==='')
    {
        $(".clienteEstado").css("border","1px solid red");
    }
    else
    {
         $(".clienteEstado").css("border","");
    }
      if(morada ==='')
    {
        $(".clienteMorada").css("border","1px solid red");
    }
    else
    {
         $(".clienteMorada").css("border","");
    }
      if(prof ==='')
    {
        $(".clienteProf").css("border","1px solid red");
    }
    else
    {
         $(".clienteProf").css("border","");
    }
      if(salario ==='')
    {
        $(".clienteSalario").css("border","1px solid red");
    }
    else
    {
         $(".clienteSalario").css("border","");
    }
      if(localidade ==='')
    {
        $(".clienteLocalidade").css("border","1px solid red");
    }
    else
    {
         $(".clienteLocalidade").css("border","");
    }
      if(localT ==='')
    {
        $(".clienteLocalT").css("border","1px solid red");
    }
    else
    {
         $(".clienteLocalT").css("border","");
    }
      if(movel ==='')
    {
        $(".clienteMovel").css("border","1px solid red");
    }
    else
    {
         $(".clienteMovel").css("border","");
    }
      if(ano ==='')
    {
        $(".clienteAno").css("border","1px solid red");
    }
    else
    {
         $(".clienteAno").css("border","");
    }
      if(mes ==='')
    {
        $(".clienteMes").css("border","1px solid red");
    }
    else
    {
         $(".clienteMes").css("border","");
    }
      if(letra ==='')
    {
        $(".clienteLetra").css("border","1px solid red");
    }
    else
    {
         $(".clienteLetra").css("border","");
    }
      if(capa ==='')
    {
        $(".clienteCapa").css("border","1px solid red");
    }
    else
    {
         $(".clienteCapa").css("border","");
    }
    
//    if (email === '')  { $(".clienteEmail").css("border","1px solid red"); }
//    else { $(".clienteEmail").css("border",""); }
}

function NifExiste(xhr, status, args)
{
     var d1 = args.nif;
     if(d1==="existe")
     {
         $(".clienteNif").val("");
         $(".clienteNif").focus();
     }
     if(d1 ==="tamanho inválido")
          $(".clienteNif").focus();
 
}

function dataNasc(xhr, status, args)
{
     var d1 = args.data;
     if(d1==="invalid")
     {
          $(".clienteDataNasc").val("");
         $(".clienteDataNasc").css("border","1px solid red");
     }
     else
     {
         $(".clienteDataNasc").css("border","");
     }
}
function redirecionar()
{
    window.location.href = "R.ClienteTest.xhtml#um";
}

function redirecionar2()
{
    window.location.href = "R.ClienteTest.xhtml#dois";
}

function openModal(xhr, status, args)
{
    var info =args.estadoP;
    var valorPagar = args.pagar;
    var numeroCheque = args.cheque;
    $(".pagamentoR").val(valorPagar);  
    $(".pagamentoPagar").val(valorPagar);
    $(".pagamentoDoc").val(numeroCheque);
    if(info !== "Pago")
    {  
        $(".modalPagamento").show($(".modalPagamento").slideDown(400));
    }
}

function limparTudo()
{
    $(":text",("#forma")).each(function()
    {
        $(this).val(""); 
        $(".clienteSexo").val("");
        $(".clienteEstado").val("");
        $(".clienteProf").val("");
        $(".clienteLocalidade").val("");
        $(".clienteLocalT").val("");
        $(".clienteAno").val("");
        $(".clienteMes").val("");
        $(".clienteLetra").val("");
        $(".clienteMorada").val("");
    });
}

function tirarCorBorda()
{
     $(":text",("#forma")).each(function()
    {
        $(this).css("border",""); 
        $(".clienteSexo").css("border",""); 
        $(".clienteEstado").css("border",""); 
        $(".clienteProf").css("border",""); 
        $(".clienteLocalidade").css("border",""); 
        $(".clienteLocalT").css("border",""); 
        $(".clienteAno").css("border",""); 
        $(".clienteMes").css("border",""); 
        $(".clienteLetra").css("border",""); 
        $(".clienteMorada").css("border",""); 
    });
}

function dataNascimentoFoco()
{
    var dataNasc = "input:text[name='forma:dataN_input']";
     $(dataNasc).focus();
}


function senhaAlterada()
{
     $(".modalChangePass").hide();
     $(".senhaAtual").val("");
     $(".novaSenha").val("");
      $(".confSenha").val("");
     $(".textInfo").html("Palavra-passe alterada com sucesso");
      $(".textInfo").css("font","14px sans-serif");
     $(".simModal").hide();
     $(".cancelar").val("Fechar");
     $(".modal").show();
}


function senhaAtualInvalida()
{
    $(".senhaAtual").css("border","1px solid red");
    $(".senhaAtual").attr("title","Palavra-passe atual inválida");
}

function senhasDiferentes()
{
     $(".novaSenha").val("");
     $(".confSenha").val("");
     $(".novaSenha").focus();
     alert("As senhas não se correspondem");
}
function alterarSenha()
  {
      var senhaAtual = $(".senhaAtual").val();
      var novaS = $(".novaSenha").val();
      var conf = $(".confSenha").val();
      if(senhaAtual ==="")
      {
          $(".senhaAtual").css("border","1px solid red");
          $(".senhaAtual").focus();
      }
      else
           $(".senhaAtual").css("border","");
       if(novaS ==="")
           $(".novaSenha").css("border","1px solid red");
       else
           $(".novaSenha").css("border","");
       if(conf==="")
           $(".confSenha").css("border","1px solid red");
       else
            $(".confSenha").css("border","");
  }
  
  function senhaCorBordaVermelha()
  {
        $(".senhaAtual").css("border","1px solid red");
  }
   function senhaCorBordaVerde()
  {
        $(".senhaAtual").css("border","1px solid green");
        $(".senhaAtual").attr("title","");
  }
  
  function clienteRegistrado()
  {
     $(".textInfo").html("Cliente registrado com sucesso");
     $(".textInfo").css("font","14px sans-serif");
     $(".simModal").hide();
     $(".cancelar").val("Fechar");
     $(".modal").show();
  }
  
 
function restricao() 
{
    $(".relatorioMenu").css("color", " #ddd");
    $(".administracaoMenu").css("color", "#ddd");
    $(".relatorioMenu").css("cursor", "none");
    $(".administracaoMenu").css("cursor", "none");
    $(".relatorioMenu").click(function (e)
    { e.preventDefault();});
     $(".administracaoMenu").click(function (e)
    { e.preventDefault();});
 }
 
 function enviarMensagem()
 {
     $(".mensagemNome").val("");
     $(".mensagemEmail").val("");
     $(".mensagemEmail").css("border","");
     $(".mensagemAssunto").val("");
     $(".mensagemInfo").val("");
       $(".mensagemEmail").attr("title","");
 }
 
 function emailInvalido()
 {
      $(".mensagemEmail").css("border","1px solid red");
      $(".mensagemEmail").focus();
      $(".mensagemEmail").attr("title","Email inválido");
 }
 function perfilAnalista() 
{
    $(".administracaoMenu").css("color", "#ddd");
    $(".administracaoMenu").css("cursor", "none");
     $(".administracaoMenu").click(function (e)
    { e.preventDefault();});
     $(".menuCliente").css("color", " #ddd");
    $(".menuCliente").css("cursor", "none");
     $(".menuCliente").click(function (e)
    { e.preventDefault();});
     $(".menuSimulacao").css("color", " #ddd");
    $(".menuSimulacao").css("cursor", "none");
     $(".menuSimulacao").click(function (e)
    { e.preventDefault();});
 }
 
 function mostrar()
    {
        PF('myPoll').start();

        $(".modalErroTest").show();
    }
     function ocultar()
    {
        $(".modalErroTest").hide();
    }
 
