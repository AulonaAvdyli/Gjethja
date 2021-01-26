import React from 'react';
import Header from "../../components/Header/Header";
import "./AboutUs.scss";
import "../Home/Home.scss"
import Footer from "../../components/Footer/Footer";
import Ick from "../../assets/images/ick.png";
import Unicef from "../../assets/images/unicef.png";
import Ada from "../../assets/images/ada.png";
import Ing from "../../assets/images/ing.png";
import Inlab from "../../assets/images/inlab.png";


class AboutUs extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            //
        }
    }

    render() {
        return (
            <div>
                <Header/>
                <div className="aboutUs">
                    <h1 className="heading">ABOUT US</h1>
                    <p className="text">Ky web aplikacion është krijuar për të përmirësuar në mënyrë dramatike gjetjen e
                        shërbimeve shtëpiake përmes përdorimit të një platforme online.
                        Duke e marrë parasysh që deri më tani gjetja e këtyre shërbimeve nuk është e lehtë dhe nuk ka
                        ndonjë ofrues për gjetjen e tyre,
                        krijimi i një aplikacioni të tillë do të kursej kohën për përdoruesit.
                        Përdorimi i këtij website do të mundësoj gjetjen e shërbimeve të ndryshme siç janë:
                        kujdesi për femijë, kujdesi për të moshuarit, kujdesi për shtëpi dhe kujdesi për shtëpi dhe
                        kujdesi për kafshë.
                        Shërbime të cilat do të ofrohen nga personat me pasqyrë profesionale për këto lloj
                        shërbimesh. </p>
                </div>
                <div className="partners">
                    <div className="partners-cards">
                        {/*<div className="partners-card-title">Miqt e Gjethes</div>*/}
                        <div className="partners-card-img">
                                <img src={Ick}/>
                            </div>
                                <div className="partners-card-img">
                                    <img src={Unicef}/>
                                </div>
                                    <div className="partners-card-img">
                                        <img src={Ada}/>
                                    </div>
                                        <div className="partners-card-img">
                                            <img src={Ing}/>
                                        </div>

                                            <div className="partners-card-img">
                                                <img src={Inlab}/>
                                            </div>
                    </div>
                </div>
                <Footer />
            </div>

        );
    }
}
export default AboutUs;
