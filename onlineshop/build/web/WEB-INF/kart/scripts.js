/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function showConfirmModal(){
    
    var idCliente = readCookie("userId");
    if(idCliente === null || idCliente === undefined){
        showLoginModal();
        return;
    }
    var sum = sumItems();
    document.getElementById("total-modal").innerText = Intl.NumberFormat("de-DE").format(sum) + " GS.";
    document.getElementById("confirm-purchase-modal").hidden = false;
    document.getElementById("container-home").setAttribute("style", "opacity:0.4");
}

function hiddenConfirmPurchase(){
    document.getElementById("confirm-purchase-modal").hidden = true;
    document.getElementById("container-home").setAttribute("style", "opacity:1");    
}