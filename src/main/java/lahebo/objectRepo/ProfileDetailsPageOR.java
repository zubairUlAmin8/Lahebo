package lahebo.objectRepo;

import org.openqa.selenium.By;

public interface ProfileDetailsPageOR {
      By editBtn =By.cssSelector("#app > div > div > section > div > div.content.p-4.md\\:p-\\[2\\.4rem\\].h-full > div > div > div > div.flex-1 > div.bg-base-white.rounded-lg.px-6.py-8.lg\\:p-10 > div > div:nth-child(1) > button.text-base-white.bg-gray-500.float-right.disabled\\:bg-gray-300.disabled\\:cursor-not-allowed.transition-colors.duration-200.hover\\:bg-gray-700.focus\\:ring-2.focus\\:ring-gray-300.font-medium.rounded.px-4.h-\\[36px\\].focus\\:outline-none.dark\\:focus\\:ring-gray-800");
      By changePasswordBtn  =By.cssSelector("#app > div > div > section > div > div.content.p-4.md\\:p-\\[2\\.4rem\\].h-full > div > div > div > div.flex-1 > div.bg-base-white.rounded-lg.px-6.py-8.lg\\:p-10 > div > div:nth-child(1) > button.mb-6.lg\\:mb-0.text-base-white.float-right.mr-3.bg-primary-500.disabled\\:bg-primary-300.disabled\\:cursor-not-allowed.transition-colors.duration-200.hover\\:bg-primary-700.focus\\:ring-2.focus\\:ring-primary-300.font-medium.rounded.px-4.h-\\[36px\\].focus\\:outline-none.dark\\:focus\\:ring-blue-800");
      By firstNameIF = By.cssSelector("#userFirstName");
      By lastNameIF = By.cssSelector("#userLastName");
      By phoneNumberIF = By.cssSelector("#userTelephone>input");
      By submitBtn = By.cssSelector("#doAction");
      By currentPasswordIF = By.cssSelector("#userOldPass");
      By newPasswordIF = By.cssSelector("#userNewPass");
      By confirmNewPasswordIF = By.cssSelector("#userConfirmPass");
      By ConfirmChangePasswordBtn = By.cssSelector("#changePassBtn");

}
