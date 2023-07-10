package lahebo.objectRepo;

import org.openqa.selenium.By;

public interface LegislationLibraryOR {
      //Legal List
      By LL_LegislationList =By.cssSelector("#searchLegislations");
      By LL_LegislationListElements =By.cssSelector("#searchLegislations>ul>li");
      By LL_DivisionList =By.cssSelector("#searchLegislationDivisions");
      By LL_DivisionListElements =By.cssSelector("#searchLegislationDivisions>ul>li");
      By LL_DescriptionIF =By.cssSelector("#legislationDesc");
      By LL_SearchBtn =By.cssSelector("#searchBtn");
      By LL_SubscribeBtn =By.cssSelector("tr:first-of-type>td:last-of-type>button");
      By LL_UnSubscribeBtn =By.cssSelector("tr:first-of-type>td:last-of-type>button");
      By LL_ConfirmSubscriptionBtn =By.cssSelector("#confirmOKBtn");




//      Legal Register
      By legalRegisterTab =By.cssSelector("#legalRegister");
      By addLegalRegisterBtn =By.cssSelector("div[class=\"text-right space-x-2\"]>button");
      By LR_DivisionList =By.cssSelector("#selectDivField");
      By LR_DivisionListElement =By.cssSelector("#selectDivField>ul>li");
      By LR_ReferenceNumber =By.cssSelector("#refNumField");
      By LR_ReferenceNumber_Edit =By.cssSelector("#legRefNumField");
      By LR_NextBtn =By.cssSelector("#nextTabBtn");
      By LR_ISOStandardList =By.cssSelector("#isoField");

      By LR_ISOStandardListListElement =By.cssSelector("#isoField>ul>li");
      By LR_ISOStandardList_Edit =By.cssSelector("#legISOField");
      By LR_ISOStandard_clear_Edit =By.cssSelector("#legISOField>div>div>button");
      By LR_LocationList =By.cssSelector("#locField");
      By LR_LocationListListElement =By.cssSelector("#locField>div>div>input");
      By LR_DepartmentList =By.cssSelector("#depField");
      By LR_DepartmentListListElement =By.cssSelector("#depField>div>div>input");
      By LR_AddControlBtn =By.cssSelector("span>svg[data-icon=\"circle-plus\"]");
      By LR_MarkAsActionCB =By.cssSelector("#markActionItem");
      By LR_AssigneeList =By.cssSelector("div[class=\"vs__dropdown-toggle\"]");
      By LR_AssigneeListElement =By.cssSelector("div[class=\"vs__dropdown-toggle\"]+ul>li");
      By LR_ContentForControlTA =By.cssSelector("textarea[class=\"form-input\"]");
      By LR_AddBtn =By.cssSelector("#doAction");
      By LR_StateIF =By.cssSelector("#stateField");
      By LR_RemarksIF =By.cssSelector("#remarksField");
      By LR_AddFinalBtn =By.cssSelector("#formActionBtn");
      By LR_DeleteLegalRegisterBtn =By.cssSelector("tr:first-of-type>td:last-of-type>div>span:last-of-type");
      By LR_EditLegalRegisterBtn =By.cssSelector("tr:first-of-type>td:last-of-type>div>span:first-of-type");
      By LR_ConfirmDeleteLegalRegisterBtn =By.cssSelector("#confirmDelBtn");

//      Subscribed Items

      By subscribeItemsTab =By.cssSelector("#subscribedItems");
      By SI_TotalItemsCount =By.cssSelector("div.text-gray-500");
      By SI_ConfirmOkBtn =By.cssSelector("#confirmOKBtn");




}
