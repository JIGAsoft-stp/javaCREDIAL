$(document).ready(function (e)
{
    
});

function mostrarRelCliente()

{  var d1="input:text[name='relCliente:dataInicial_input']";
    var d2 ="input:text[name='relCliente:dataF_input']";
    if($(d1).val()!=="" && $(d2).val()!=="")
    {
        $(".modalImage").show();
    }
         
}
function fecharProcessamento()
{
    $(".modalImage").hide();
}

function mostrarRelCreditoObtido()
{
    var d1="input:text[name='QCredito:dataIQ_input']";
    var d2 ="input:text[name='QCredito:dataFINAL_input']";
    var localidade = $(".localidadeCredito").val();
    var localT = $(".localTCredito").val();
    var desativado = $(".localidadeCredito").is(":disabled");
    if(desativado ===true)
    {
      if($(d1).val()!=="" && $(d2).val()!=="")
         $(".modalImage").show();
    }
    else
    {
         if($(d1).val()!=="" && $(d2).val()!=="" && localidade!=="" && localT!=="")
             $(".modalImage").show();
    }
    
}

function mostrarRelCrescHomologo()
{
    var d1="input:text[name='formmHoform:dataIHomologo_input']";
    var d2 ="input:text[name='formmHoform:dataFHomologo_input']";
    var periodo = $(".peridoHomologo").val();

    if($(d1).val()!=="" && $(d2).val()!=="" && periodo!=="")
         $(".modalImage").show();
   
}


function mostrarRelCreditoConcebido()
{
    var d1="input:text[name='infoCredito:dataInicialCredito_input']";
    var d2 ="input:text[name='infoCredito:dataFinalCredito_input']";
    var periodo = $(".periodoCredito").val();
    var tipoC = $(".tipCred").val();
    if($(d1).val()!=="" && $(d2).val()!=="" && periodo!=="" && tipoC!=="")
         $(".modalImage").show();
   
}

function mostrarRelCreditoCobranca()
{
    var d1="input:text[name='relatorioCobranca:dataInicialCobranca_input']";
    var d2 ="input:text[name='relatorioCobranca:dataFinalCobranca_input']";
    var periodo = $(".cobrancaPeriodoC").val();
    var tipoC = $(".tipCred").val();
    var banco =$(".banco").val();
    if($(d1).val()!=="" && $(d2).val()!=="" && periodo!=="" && tipoC!=="" && banco!=="")
         $(".modalImage").show();
   
}

function mostrarRelDividaCapital()
{
    var d1="input:text[name='relatorioDividaCapital:dataInicialDividaCapital_input']";
    var d2 ="input:text[name='relatorioDividaCapital:dataFinalDividaCapital_input']";
    var periodo = $(".periodoComp").val();
    if($(d1).val()!=="" && $(d2).val()!=="" && periodo!=="")
         $(".modalImage").show();
   
}

function mostrarRelDividaProduto()
{
    var d1="input:text[name='relatorioDividaProduto:dataInicialDividaP_input']";
    var d2 ="input:text[name='relatorioDividaProduto:dataFinalDividaP_input']";
    var localT = $(".localTdividaP").val();
    var tipoC = $(".tipoCDividaP").val();

    if($(d1).val()!=="" && $(d2).val()!=="" && localT!=="" && tipoC!=="")
         $(".modalImage").show();
   
}

function mostrarRelCheques()
{
    var d1="input:text[name='relatorioCheque:chequeDataInicial_input']";
    var d2 ="input:text[name='relatorioCheque:chequeDataFinal_input']";
    var localT = $(".localTCheque").val();
    var banco = $(".bancoCheque").val();

    if($(d1).val()!=="" && $(d2).val()!=="" && localT!=="" && banco!=="")
         $(".modalImage").show();
   
}