$(document).ready(function()
{
if ($("#alertSuccess").text().trim() == "")
 {
 $("#alertSuccess").hide();
 }
 $("#alertError").hide();
});




//SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
// Clear alerts---------------------
 $("#alertSuccess").text("");
 $("#alertSuccess").hide();
 $("#alertError").text("");
 $("#alertError").hide();
// Form validation-------------------
var status = validateOrderForm();
if (status != true)
 {
 $("#alertError").text(status);
 $("#alertError").show();
 return;
 }

var type = ($("#hidoIdSave").val() == "") ? "POST" : "PUT";


$.ajax(
		{
		 url : "OrderAPI",
		 type : type,
		 data : $("#formOrder").serialize(),
		 dataType : "text",
		 complete : function(response, status)
		 {
		 onOrderSaveComplete(response.responseText, status);
		 }
		});

});
function onOrderSaveComplete(response, status)
{
if (status == "success")
 {
	var resultSet = JSON.parse(response);
	if (resultSet.status.trim() == "success")
	{
		$("#alertSuccess").text("Successfully saved.");
		$("#alertSuccess").show();
		
		$("#divOrderGrid").html(resultSet.data);
	} else if (resultSet.status.trim() == "error")
	{
		$("#alertError").text(resultSet.data);
		$("#alertError").show();
	}
 	} else if (status == "error")
 	{
 		$("#alertError").text("Error while saving.");
 		$("#alertError").show();
 	} else
 	{
 		$("#alertError").text("Unknown error while saving..");
 		$("#alertError").show();
 	}
		$("#hidoIdSave").val("");
		$("#formOrder")[0].reset();
}

//UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
 $("#hidoIdSave").val($(this).closest("tr").find('#hidoIdUpdate').val());
 $("#orderId").val($(this).closest("tr").find('td:eq(0)').text());
 $("#userId").val($(this).closest("tr").find('td:eq(1)').text());
 $("#fundingBodiesId").val($(this).closest("tr").find('td:eq(2)').text());
 $("#researchProjectId").val($(this).closest("tr").find('td:eq(3)').text());
 $("#researchProjectFundId").val($(this).closest("tr").find('td:eq(4)').text());
 $("#orderTotalAmount").val($(this).closest("tr").find('td:eq(5)').text());


});


$(document).on("click", ".btnRemove", function(event)
		{
		 $.ajax(
		 {
		 url : "OrderAPI",
		 type : "DELETE",
		 data : "oId=" + $(this).data("itemid"),
		 dataType : "text",
		 complete : function(response, status)
		 {
		 onOrderDeleteComplete(response.responseText, status);
		 }
		 });
		});

function onOrderDeleteComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully deleted.");
 $("#alertSuccess").show();
 $("#divOrderGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while deleting.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while deleting..");
 $("#alertError").show();
 }
}

//CLIENTMODEL=========================================================================
function validateOrderForm()
{
	
//order id
if ($("#orderId").val().trim() == "")
{
return "Insert order Id.";
}

//user id
if ($("#userId").val().trim() == "")
{
return "Insert User Id.";
}


//fundingBodiesId
if ($("#fundingBodiesId").val().trim() == "")
{
return "Insert Funding Bodies ID.";
} 

//Research Project
if ($("#researchProjectId").val().trim() == "")
{
return "Insert Research Project.";
}

//Research Project Fund ID
if ($("#researchProjectFundId").val().trim() == "")
{
return "Insert Research Project Fund ID.";
}

//Doctor Charge
if ($("#orderTotalAmount").val().trim() == "")
{
return "Total Aamount Charge.";
}
//is numerical valuee
var tmpCharge = $("#orderTotalAmount").val().trim();
if (!$.isNumeric(tmpCharge))
{
return "Insert a numerical value for order Charge.";
}
$("#orderTotalAmount").val(parseFloat(tmpCharge).toFixed(6));

return true;
}


