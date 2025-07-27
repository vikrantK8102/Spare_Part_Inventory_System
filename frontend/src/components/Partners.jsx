import React from 'react';
import Marquee from 'react-fast-marquee';
import img1 from '../images/carLogo/9.png';
import img2 from '../images/carLogo/10.png';
import img3 from '../images/carLogo/11.png';
import img4 from '../images/carLogo/12.png';
import img5 from '../images/carLogo/13.png';
import img6 from '../images/carLogo/14.png';
import img7 from '../images/carLogo/15.png';
import img8 from '../images/carLogo/16.png';
import img9 from '../images/carLogo/17.png';
import img10 from '../images/carLogo/18.png';
import img11 from '../images/carLogo/19.png';
import img12 from '../images/carLogo/20.png';
import img13 from '../images/carLogo/21.png';
import img14 from '../images/carLogo/22.png';
import img15 from '../images/carLogo/23.png';
import img16 from '../images/carLogo/24.png';
import img17 from '../images/carLogo/25.png';
import img18 from '../images/carLogo/26.png';
import img19 from '../images/carLogo/27.png';
import img20 from '../images/carLogo/28.png';
import img21 from '../images/carLogo/29.png';
import img22 from '../images/carLogo/30.png';
import img23 from '../images/carLogo/31.png';
import img24 from '../images/carLogo/32.png';

import './Partner.css';

function Partner() {
  return (
    <>
      <hr />
      <div className="partner-section">
        <div className="title">
          <h1>Our Manufactures Partner</h1>
        </div>

        <div className="marquee-wrapper">
          <Marquee direction="right" speed={60} pauseOnHover gradient={false}>
            {[img1, img2, img3, img4, img5, img6, img7, img8, img9, img10, img11, img12].map((img, index) => (
              <div className="image_wrapper" key={index}>
                <img src={img} alt={`logo-${index}`} />
              </div>
            ))}
          </Marquee>
        </div>

        <div className="marquee-wrapper">
          <Marquee direction="left" speed={60} pauseOnHover gradient={false}>
            {[img13, img14, img15, img16, img17, img18, img19, img20, img21, img22, img23, img24].map((img, index) => (
              <div className="image_wrapper" key={index}>
                <img src={img} alt={`logo2-${index}`} />
              </div>
            ))}
          </Marquee>
        </div>
      </div>
    </>
  );
}

export default Partner;
