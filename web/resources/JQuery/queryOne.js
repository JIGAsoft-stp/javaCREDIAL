/* 
    Created on : Aug 16, 2015, 5:28:48 PM
    Author     : Junior
*/
$(document).ready(function(e) {
     
     $(".fecharModalPagamento").click(function (e)
     {
        e.preventDefault();
        fecharModal();
     });
//     $(".pagamentoFonte").click(function (e)
//     {
//         e.preventDefault();
//           var fonte = $("input:radio[name='forma:modalPagamento:fonteP']").val();
//           alert(fonte);
//     });
//       

     $(".pagamentoBotao").click(function (e)
     {
         e.preventDefault();
        var numDoc = $(".pagamentoDoc").val();
        var campoDocumentoAtivado = $(".pagamentoDoc").is(":disabled");
        var montantePagar = $(".pagamentoPagar").is(":disabled");
        var data= "input:text[name='forma:modalPagamento:Pdata_input']";
        var montante = $(".pagamentoPagar").val();
        
        if(campoDocumentoAtivado === false )
        {
            if(montantePagar === true)
            {
                if(numDoc ==='')
                     $(".pagamentoDoc").css("border","1px solid red");
                else
                 $(".pagamentoDoc").css("border","");
            }
            else
            {
                if(numDoc ==='')
                    $(".pagamentoDoc").css("border","1px solid red");
                else
                    $(".pagamentoDoc").css("border","");
                if(montante ==='')
                    $(".pagamentoPagar").css("border","1px solid red");
                else
                   $(".pagamentoPagar").css("border","");
               if($(data).val() ==="")
                   $(data).css("border","1px solid red");
               else
                   $(data).css("border","");
            }
        }
 
     });
    $('.closeModalFrame').click(function (){
        var data= "input:text[name='forma:modalPagamento:Pdata_input']";
          $(".modalPagamento").hide($(".modalPagamento").fadeOut(800));  
      $(":radio").each(function ()
      {
         $(this).prop({checked: false});
      });
      $(".pagamentoDoc").css("border","");
      $(".pagamentoPagar").css("border","");
      $(data).val("");
      $(data).css("border","");
      $(data).attr('disabled',true);
      $(".pagamentoDoc").val("");
      $(".pagamentoPagar").val(""); 
      $(".pagamentoR").val(""); 
       $(".pagamentoPagar").attr('disabled',true);
      $(".pagamentoData").attr('disabled',true);
      $("#Pbanco").attr('disabled',true);
      $(".pagamentoDoc").attr('disabled',true);
      $("#tipoP").attr('disabled',true);
    
    }); 
   
    $('.cancel').click(function (e){
      e.preventDefault();
       var data= "input:text[name='forma:modalPagamento:Pdata_input']";
      $(".pagamentoDoc").css("border","");
      $(".pagamentoPagar").css("border","");
       $(":radio").each(function ()
      {
         $(this).prop({checked: false});
      });
      $(data).val("");
      $(data).css("border","");
      $(data).attr('disabled',true);
      $(".pagamentoDoc").val("");
      $(".pagamentoPagar").val(""); 
      $(".pagamentoR").val(""); 
       $(".pagamentoPagar").attr('disabled',true);
      $(".pagamentoData").attr('disabled',true);
      $("#Pbanco").attr('disabled',true);
      $(".pagamentoDoc").attr('disabled',true);
      $("#tipoP").attr('disabled',true);
      $(".modalPagamento").hide($(".modalPagamento").fadeOut(400)); 
      
    });     

    $(".pagamentoNumero").keyup(function(e){
    e.preventDefault();
        var expre = /[^0-9]/g;
        // REMOVE OS CARACTERES DA EXPRESSAO ACIMA
        if ($(this).val().match(expre))
            $(this).val($(this).val().replace(expre,''));
   });
   
   
});


function fechar()
{
       var data= "input:text[name='forma:modalPagamento:Pdata_input']";
      $(":radio").each(function ()
      {
         $(this).prop({checked: false});
      });
      $(".pagamentoDoc").css("border","");
      $(".pagamentoPagar").css("border","");
      $(data).val("");
      $(data).css("border","");
      $(data).attr('disabled',true);
      $(".pagamentoDoc").val("");
      $(".pagamentoPagar").val(""); 
      $(".pagamentoR").val(""); 
       $(".pagamentoPagar").attr('disabled',true);
      $(".pagamentoData").attr('disabled',true);
      $("#Pbanco").attr('disabled',true);
      $(".pagamentoDoc").attr('disabled',true);
      $("input:radio[name='forma:modalPagamento:tipoP']").attr('disabled',true);
      $("input:radio[name='forma:modalPagamento:fonteP']").val("");
      $("input:radio[name='forma:modalPagamento:tipoP']").val("");
      $(".modalPagamento").hide($(".modalPagamento").fadeOut(800));  
}

function fecharModal()
{
     var data= "input:text[name='forma:modalPagamento:Pdata_input']";
      $(":radio").each(function ()
      {
         $(this).prop({checked: false});
      });
      $(".pagamentoDoc").css("border","");
      $(".pagamentoPagar").css("border","");
      $(data).val("");
      $(data).css("border","");
      $(data).attr('disabled',true);
      $(".pagamentoDoc").val("");
      $(".pagamentoPagar").val(""); 
      $(".pagamentoR").val(""); 
       $(".pagamentoPagar").attr('disabled',true);
      $(".pagamentoData").attr('disabled',true);
      $("#Pbanco").attr('disabled',true);
      $(".pagamentoDoc").attr('disabled',true);
      $("input:radio[name='forma:modalPagamento:tipoP']").attr('disabled',true);
      $(".modalPagamento").hide($(".modalPagamento").fadeOut(800)); 
      window.location.href = "R.ClienteTest.xhtml#um";
}