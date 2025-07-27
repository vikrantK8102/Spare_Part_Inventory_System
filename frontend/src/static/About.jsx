import React from 'react';
import '../static/About.css';
import computer from "../images/computer.png";
import target from "../images/target.png";
import confued from "../images/confused.png";

function About() {
    return (
        <>
            <main role="main">
                <div className="container about_text">
                    <hr />
                    <div className="row">
                        {/* Vision */}
                        <div className="col-md-4">
                            <div className="text-center mt-2">
                                <img src={computer} alt="Vision" className="bd-placeholder-img" width="140" height="140" />
                                <h2>Our Vision</h2>
                            </div>
                            <p className="justify">
                                At <strong>SparePartWala</strong>, our vision is to revolutionize the vehicle spare parts industry in India by creating a seamless digital ecosystem for buyers and sellers. We aim to become the go-to destination for all types of vehicle components—whether you're a two-wheeler owner, a car enthusiast, or a service garage.
                                <br /><br />
                                We envision a future where anyone across India—urban or rural—can access genuine, affordable, and certified spare parts without being dependent on offline stores or middlemen.
                            </p>
                        </div>

                        {/* Mission */}
                        <div className="col-md-4">
                            <div className="text-center mt-2">
                                <img src={target} alt="Mission" className="bd-placeholder-img" width="140" height="140" />
                                <h2>Our Mission</h2>
                            </div>
                            <p className="justify">
                                Our mission is to deliver a smart, transparent, and convenient platform that empowers:
                                <ul>
                                    <li><strong>Customers</strong> – to easily find, compare, and order spare parts from their mobile or desktop.</li>
                                    <li><strong>Workshops & Garages</strong> – to streamline inventory and order tracking.</li>
                                    <li><strong>Local Sellers</strong> – to digitize their business and reach a wider customer base.</li>
                                </ul>
                                We are committed to innovation, trust, and customer satisfaction while offering a wide range of high-quality spare parts for bikes, cars, trucks, and more.
                            </p>
                        </div>

                        {/* Why Us */}
                        <div className="col-md-4">
                            <div className="text-center mt-2">
                                <img src={confued} alt="Why Us" className="bd-placeholder-img" width="140" height="140" />
                                <h2>Why Choose SparePartWala?</h2>
                            </div>
                            <p className="justify">
                                <strong>1. Trusted Suppliers:</strong> We partner only with verified sellers and manufacturers.<br />
                                <strong>2. Accurate Fitment:</strong> Find parts that match your exact vehicle make, model, and year.<br />
                                <strong>3. Multiple Roles:</strong> Admins, Companies, Shippers, Sellers, Customers & Delivery Boys – all managed efficiently.<br />
                                <strong>4. Real-time Tracking:</strong> Know where your orders are at any point.<br />
                                <strong>5. Secure Payments:</strong> Integrated with Razorpay for smooth transactions.<br />
                                <strong>6. All India Delivery:</strong> Fast shipping with active delivery partner network.<br />
                                <strong>7. Tech-Driven:</strong> Built using React JS, Java Spring Boot, ASP.NET Core, and MySQL – robust and scalable.
                            </p>
                        </div>
                    </div>
                    <hr />
                </div>
            </main>
        </>
    );
}

export default About;
