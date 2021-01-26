import React from "react";
import styled from "styled-components"
import './Footer.scss'

function Footer() {
    return(
        <div>
            <div className="footer">
                <div className="contact">
                    <div className="contact-title">Na kontaktoni:</div>
                    <ul>
                        <li className="contact-text">10000 Pristina, Kosovo</li>
                        <li className="contact-text">+ 383 (0)44 111 111</li>
                        <li className="contact-text">katrasolutions@gmail.com</li>
                    </ul>
                </div>
                <div className="social-media">
                    <div className="social-media-title">Rrjetet Sociale:</div>
                    <ul>
                        <li><a href="https://www.facebook.com/katrasolutions/">Facebook</a></li>
                        <li><a href="https://www.instagram.com/katrasolutions/">Instagram</a></li>
                        <li><a href="https://www.linkedin.com/company/katra-solutions">LinkedIn</a></li>
                    </ul>

                </div>
                <div className="copyrights">
                    <p>2020 Gjethja - All Rights Reserved</p>
                </div>
            </div>
        </div>
    )
}
export default Footer;

