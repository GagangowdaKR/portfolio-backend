package com.gkr.portfolio_backend.dto;

import com.gkr.portfolio_backend.model.Contact;

public record MailPayload(String name, String email, Contact.Subject subject, String profession, String message) {

    /**
     * Dynamically generates a clean, structured text body.
     * * @return Formatted multi-line email string layout.
     */
    public String toSelfEmailBody() {
        return """
               You have received a new message from your portfolio website!
               
               👤 Name: %s
               📧 Email: %s
               💼 Profession: %s
               
               💬 Message:
                          %s
               """.formatted(name, email, profession, message);
    }

    /**
     * Formats a standardized subject header for your inbox sorting.
     */
    public String toSelfEmailSubject() {
        return "🚀 Portfolio Alert: " + subject;
    }

    /**
     * CONTACT REQUEST SENT CONFIRMATION MAIL FOR THE VISITORS
     */

    public String toConfirmEmailSubject() {
        return "Confirmation : Portfolio inquiry sent !";
    }

    public String toConfirmEmailBody() {
        return """
           <html>
             <body style="font-family: Arial, sans-serif; color: #222; line-height: 1.5;">
               <p>Hey  %s,</p>
               <p>
                 Thank you for your interest in connecting. Your request has been submitted,
                 and I will reach out to you shortly.
               </p>
               <p>Regards,</p>

               <!-- Email footer start -->
               <div style="font-family: Arial, sans-serif; color: #222; line-height: 1.4;">

                 <!-- Name -->
                 <div style="font-size: 20px; font-weight: bold;">
                   <span style="color: #FF8800;">Gagan Gowda K R</span>
                 </div>

                 <!-- Role -->
                 <div style="font-size: 14px; font-weight: bold; color: #80BFFF; margin-top: 2px;">
                   Software Developer
                 </div>

                 <hr style="border: 0; border-top: 1px solid #e0e0e0; margin-top: 8px;">

                 <!-- Links with icons -->
                 <div style="font-size: 13px; color: #555; margin-top: 6px;">

                   <!-- Portfolio -->
                   <span style="margin-right: 8px; display: inline-flex; align-items: center;">
                     <svg width="15" height="15" viewBox="0 0 32 32" fill="none"
                          xmlns="http://www.w3.org/2000/svg" style="margin-right: 4px;">
                       <g clip-path="url(#clip0_2_20)">
                         <path d="M5.66667 0H26.3333C29.4667 0 32 2.53333 32 5.66667V25.5333C32 28.6667 29.4667 31.2 26.3333 31.2H5.66667C2.53333 31.2 0 28.6667 0 25.5333V5.66667C0 2.53333 2.53333 0 5.66667 0Z" fill="#000B1D"/>
                         <path d="M7.60004 21.8V8.15999C7.60004 8.06665 7.64004 8.01332 7.73337 8.01332C7.96004 8.01332 8.24004 8.01332 8.5467 7.99999C8.8667 7.98665 9.20004 7.98665 9.56004 7.97332C9.92004 7.95999 10.3067 7.95999 10.72 7.94665C11.1334 7.93332 11.5334 7.93332 11.9334 7.93332C13.0267 7.93332 13.9334 8.06665 14.68 8.34665C15.3467 8.57332 15.96 8.94665 16.4667 9.43999C16.8934 9.86665 17.2267 10.3867 17.44 10.96C17.64 11.52 17.7467 12.0933 17.7467 12.6933C17.7467 13.84 17.48 14.7867 16.9467 15.5333C16.4134 16.28 15.6667 16.84 14.8 17.16C13.84 17.5067 12.8134 17.68 11.8 17.6667C11.48 17.6667 11.2667 17.6667 11.1334 17.6533C11 17.64 10.76 17.68 10.5067 17.68V21.84C10.52 21.9333 10.4534 22.0133 10.36 22.0267C10.3467 22.0267 10.3334 22.0267 10.3067 22.0267L7.7467 21.9867C7.65337 21.9733 7.60004 21.92 7.60004 21.8ZM10.5067 10.6267V15.0667C10.6934 15.08 10.92 15.0533 11.08 15.0533H11.7867C12.3067 15.0667 12.8267 14.9867 13.32 14.84C13.7467 14.72 14.12 14.4667 14.4134 14.1333C14.6934 13.8 14.8267 13.3467 14.8267 12.76C14.84 12.3467 14.7334 11.9333 14.52 11.5733C14.2934 11.2267 13.9734 10.96 13.5867 10.8133C13.0934 10.6133 12.56 10.5333 12.0134 10.5467C11.6667 10.5467 11.36 10.5467 11.1067 10.56C10.9067 10.56 10.7067 10.5867 10.5067 10.6267Z" fill="white"/>
                         <path d="M20.5334 13.8533H19.2134C19.1067 13.84 19.0667 13.7867 19.0667 13.7067V11.5067C19.0534 11.44 19.1067 11.3733 19.1734 11.36C19.1867 11.36 19.2 11.36 19.2134 11.36H20.5334V11.0533C20.5334 10.6 20.56 10.16 20.64 9.70668C20.7067 9.33335 20.8267 8.97334 20.9867 8.62668C21.2667 8.05334 21.68 7.56001 22.2134 7.18668C22.7467 6.81335 23.44 6.62668 24.32 6.62668C24.5334 6.62668 24.7334 6.64001 24.9467 6.65335C25.12 6.66668 25.28 6.70668 25.44 6.77334C25.5334 6.80001 25.5867 6.89335 25.5867 6.98668V9.10668C25.5867 9.21335 25.5334 9.24001 25.4134 9.21335C25.3067 9.20001 25.2 9.18668 25.0934 9.18668H24.7734C24.5334 9.18668 24.3067 9.22668 24.0934 9.33335C23.8934 9.44001 23.7334 9.62668 23.6667 9.84001C23.5334 10.1467 23.4667 10.4667 23.4534 10.8V11.3467H25.2667C25.3467 11.3467 25.4 11.36 25.4267 11.3867C25.4534 11.4267 25.4667 11.4667 25.4534 11.52V13.7067C25.4534 13.8133 25.3867 13.8533 25.2667 13.8533H23.4534V21.7867C23.4534 21.8267 23.44 21.88 23.4267 21.92C23.4 21.96 23.3467 21.9867 23.2667 21.9867H20.72C20.6 21.9867 20.5467 21.92 20.5467 21.8V13.8533H20.5334Z" fill="white"/>
                       </g>
                       <defs>
                         <clipPath id="clip0_2_20">
                           <rect width="32" height="31.2" fill="white"/>
                         </clipPath>
                       </defs>
                     </svg>
                     <a href="https://gkr-portfolio.vercel.app/"
                        style="color: #0077FF; text-decoration: none; font-weight: 500;">
                       Portfolio
                     </a>
                   </span>

                   <!-- Separator -->
                   <span style="color: #999;">|</span>

                   <!-- GitHub -->
                   <span style="margin: 0 8px; display: inline-flex; align-items: center;">
                     <svg width="15" height="15" xmlns="http://www.w3.org/2000/svg"
                          viewBox="0 0 128 128" style="margin-right: 4px;">
                       <!-- GitHub SVG as you provided -->
                       <defs><linearGradient id="a" x1="11.622" x2="11.622" y1="7.023" y2="13.825" gradientTransform="translate(.5 .592) scale(7.9375)" gradientUnits="userSpaceOnUse"><stop offset="0" stop-color="#0196CA"/><stop offset="1" stop-color="#0065A9"/></linearGradient><linearGradient id="b" x1="11.622" x2="11.622" y1="8.675" y2="15.977" gradientTransform="translate(.5 .592) scale(7.9375)" gradientUnits="userSpaceOnUse"><stop offset="0" stop-color="#01A7DE"/><stop offset="1" stop-color="#007ACC"/></linearGradient><linearGradient id="c" x1="14.594" x2="14.594" y1="7.023" y2="15.977" gradientTransform="translate(.5 .592) scale(7.9375)" gradientUnits="userSpaceOnUse"><stop offset="0" stop-color="#2EC2F6"/><stop offset="1" stop-color="#1F9CF0"/></linearGradient></defs><path fill="#24292e" d="M.5 64.092C.5 29.008 28.916.592 64 .592c29.601 0 54.457 20.23 61.501 47.625h-24.065a25.436 25.436 0 0 0-3.305-4.524c.635-1.588 2.857-8.097-.635-16.828 0 0-5.318-1.747-17.462 6.508-5.08-1.428-10.478-2.143-15.875-2.143s-10.795.715-15.875 2.143c-12.144-8.175-17.462-6.508-17.462-6.508-3.493 8.73-1.27 15.24-.635 16.828-4.048 4.445-6.509 10.16-6.509 17.066 0 17.057 7.281 24.836 16.51 28.507v12.959c-3.492-.284-7.264-1.992-10.16-6.858-1.19-1.905-4.762-6.588-9.763-6.51-5.318.08-2.143 3.017.08 4.208 2.698 1.508 5.794 7.143 6.508 8.97 1.024 2.878 3.903 7.87 13.335 8.162v12.767C16.906 113.541.501 90.788.501 64.093z"/><path fill="url(#a)" d="m124.97 63.867-14.719-7.087a4.45 4.45 0 0 0-5.077.863l-46.2 39.965a2.977 2.977 0 0 0 .003 4.402l7.904 7.547a2.977 2.977 0 0 0 3.801.169l40.941-31.744 9.647-10.638c.988-1.09 2.1-2.382 3.563-2.23 1.412.148 2.665 1.31 2.665 2.946v-.17a4.465 4.465 0 0 0-2.528-4.023z"/><path fill="url(#b)" d="m124.97 119.88-14.719 7.087a4.449 4.449 0 0 1-5.077-.863l-46.2-43.933a2.977 2.977 0 0 1 .003-4.403l7.904-7.546a2.977 2.977 0 0 1 3.801-.17l40.941 35.713 9.647 10.639c.988 1.09 2.1 2.382 3.563 2.23 1.413-.149 2.665-1.311 2.665-2.947v.171a4.465 4.465 0 0 1-2.528 4.023z"/><path fill="url(#c)" d="M110.25 126.97a4.451 4.451 0 0 1-5.078-.864c1.648 1.647 6.45.48 6.45-1.85V59.494c0-2.33-4.802-3.497-6.45-1.85a4.452 4.452 0 0 1 5.078-.863l14.717 5.093a4.465 4.465 0 0 1 2.53 4.023v51.956c0 1.716-.984 3.28-2.53 4.024z"/></svg>
                     <a href="https://github.com/GagangowdaKR"
                        style="color: #0077FF; text-decoration: none; font-weight: 500;">
                       GitHub
                     </a>
                   </span>

                   <!-- Separator -->
                   <span style="color: #999;">|</span>

                   <!-- LinkedIn -->
                   <span style="margin-left: 8px; display: inline-flex; align-items: center;">
                     <svg width="15" height="15" xmlns="http://www.w3.org/2000/svg"
                          preserveAspectRatio="xMidYMid" viewBox="0 0 256 256" style="margin-right: 4px;">
                       <path d="M218.123 218.127h-37.931v-59.403c0-14.165-.253-32.4-19.728-32.4-19.756 0-22.779 15.434-22.779 31.369v60.43h-37.93V95.967h36.413v16.694h.51a39.907 39.907 0 0 1 35.928-19.733c38.445 0 45.533 25.288 45.533 58.186l-.016 67.013ZM56.955 79.27c-12.157.002-22.014-9.852-22.016-22.009-.002-12.157 9.851-22.014 22.008-22.016 12.157-.003 22.014 9.851 22.016 22.008A22.013 22.013 0 0 1 56.955 79.27m18.966 138.858H37.95V95.967h37.97v122.16ZM237.033.018H18.89C8.58-.098.125 8.161-.001 18.471v219.053c.122 10.315 8.576 18.582 18.89 18.474h218.144c10.336.128 18.823-8.139 18.966-18.474V18.454c-.147-10.33-8.635-18.588-18.966-18.453" fill="#0A66C2"/>
                     </svg>
                     <a href="https://www.linkedin.com/in/gagan-gowda-k-r/"
                        style="color: #0077FF; text-decoration: none; font-weight: 500;">
                       LinkedIn
                     </a>
                   </span>

                 </div>
               </div>
               <!-- Email footer end -->
             </body>
           </html>
           """.formatted(name);
    }
}