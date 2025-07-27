// src/components/TopSlider.jsx
import React from 'react';
import Carousel from 'react-bootstrap/Carousel';
import { ButtonGroup, Button } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';
import { useSelector } from 'react-redux';
import HorizonatalCard from './HorizonatalCard';
import './TopSlider.css';

// Image imports
import slide2 from '../images/2.jpg';
import slide3 from '../images/3.jpg';
import slide4 from '../images/4.jpg';
import slide5 from '../images/5.jpg';
import slide6 from '../images/6.jpg';
import slide7 from '../images/7.jpg';
import slide8 from '../images/8.jpg';

const TopSlider = () => {
  const navigate = useNavigate();
  const { loggedin } = useSelector((state) => state);

  const slides = [slide2, slide3, slide4, slide5, slide6, slide7, slide8];

  return (
    <>
      <div className="top-slider">
        <Carousel interval={3000} fade controls indicators>
          {slides.map((src, index) => (
            <Carousel.Item key={index}>
              <div className="slide-container">
                <img
                  src={src}
                  alt={`Slide ${index + 1}`}
                  className="slide-image"
                />
              </div>

              {/* Show buttons on slide 6 (2nd last) if not logged in */}
              {index === slides.length - 2 && !loggedin?.IsLoggedIn && (
                <Carousel.Caption>
                  <ButtonGroup size="lg" className="mb-3">
                    <Button
                      variant="info"
                      className="me-2"
                      onClick={() => navigate('/login')}
                    >
                      Login
                    </Button>
                    <Button
                      variant="success"
                      onClick={() => navigate('/register')}
                    >
                      Signup
                    </Button>
                  </ButtonGroup>
                </Carousel.Caption>
              )}
            </Carousel.Item>
          ))}
        </Carousel>
      </div>

      <HorizonatalCard />
    </>
  );
};

export default TopSlider;
