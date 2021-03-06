package com.example.demo.exception;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ExceptionMessage {

    //General message
    public static final String INPUT_NOT_CORRECT = "ورودی درست نیست";
    //userMessage
    public static final String PASSWORD_DOESNT_MATCH = "پسورد و تایید پسورد همخوانی ندارند";
    public static final String PASSWORD_IS_NOT_STRONG = "رمز عبور قوی نیست!رمزعبور قویتری انتخاب کنید";
    public static final String EMAIL_IS_NOT_VALID = "ایمیل معتبر نیست";
    public static final String EMAIL_HAS_BEEN_REGISTERED = "ایمیل از قبل ثبت شده است";
    public static final String YOU_DONT_HAVE_ACCESS_TO_THIS_JOB = "شما به این شغل ذسترسی ندارید";
    public static final String FIRST_NAME_MUST_BE_FILLED = "نام باید پر شده باشد";
    public static final String FIRST_NAME_MUST_BE_BETWEEN_2_AND_20 = "طول نام  می بایست بین 2 و 20 باشد";
    public static final String FIRST_NAME_MUST_BE_LETTER = " نام می بایست فقط شامل حروف فارسی  باشد";
    public static final String LAST_NAME_MUST_BE_FILLED = "نام خانوادگی باید پر شده باشد";
    public static final String EMAIL_MUST_BE_FILLED = "ایمیل باید پر شده باشد";
    public static final String PASSWORD_MUST_BE_FILLED = "رمز باید پر شده باشد";
    public static final String LAST_NAME_MUST_BE_BETWEEN_2_AND_20 = "طول نام خانوادگی می بایست بین 2 و 20 باشد";
    public static final String LAST_NAME_MUST_BE_LETTER = " نام  خانوادگی می بایست فقط شامل حروف فارسی باشد";

    //CompanyMessage
    public static final String COMPANY_AND_CITY_HAVE_BEEN_REGISTERED = "این شرکت در این شهر قبلا ثبت شده است";
    public static final String COMPANY_NAME_MUST_BE_LETTER = " نام  شرکت می بایست فقط شامل حروف فارسی باشد";
    public static final String COMPANY_NAME_IS_REQUIRED = " نام  شرکت اجباریست";
    public static final String COMPANY_ADDRESS_IS_REQUIRED = " آدرس  شرکت اجباریست";
    public static final String ABOUT_COMPANY_IS_REQUIRED = " درباره شرکت اجباریست";
    public static final String CITY_IS_NOT_VALID = " شهر معتبر نمی باشد";
    public static final String ROLE_IS_NOT_FOUND="Role مشخض نشده است";

    //JobMessage
    public static final String JOB_TITLE_MUST_BE_FILLED = "عنوان شغلی باید پر شده باشد";
    public static final String JOB_SKILLS_MUST_BE_FILLED = "توانایی ها می بایست پر شده باشند";
    public static final String ABOUT_JOB_MUST_BE_FILLED = "توضیحات درباره شغل می بایست پر شده باشند";
    public static final String JOB_GROUP_MUST_BE_FILLED = "گروه شغلی می بایست پر شده باشند";
    public static final String SALARY_MUST_BE_POSITIVE = "حقوق می بایست عددی مثبت باشد";
    public static final String JOB_HAS_BEEN_DECLARED_IN_TEN_PAST_DAYS = "شغلی با این عنوان در ده روز گذشته ثبت شده است";
    public static final String MORE_THAN_TEN_JOB_HAS_BEEN_SAVED_FOR_THIS_COMPANY = "شما بیش از ده آگهی فعال دارید.روز بعد تلاش کنید";

    //ResumeMessage
    public static final String YOU_HAVE_BEEN_SENT_RESUME = "شما قبلا برای این موقعیت شغلی رزومه ارسال کرده اید";
    public static final String RESUME_UPLOADED_SUCCESSFULLY = "رزومه با موفقیت ارسال شد";
    public static final String FILE_COULD_NOT_UPLOAD = "فایل اپلود نشد";
    public static final String NO_RESUME_HAS_BEEN_UPLOADED_FOR_THIS_JOB="رزومه ای برای این آگهی  ارسال نشده است";
    public static final String FILE_MUST_BE_IN_PDF="نوع فایل می بایست پی دی اف باشد";
}
