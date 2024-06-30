import React from 'react';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

const Home = () => {
    const styles = {
        container: {
            display: 'flex',
            flexDirection: 'column',
            alignItems: 'center',
            justifyContent: 'center',
            height: '100vh',
            background: 'linear-gradient(135deg, #6e8efb, #a777e3)',
            color: 'white',
            textAlign: 'center',
            fontFamily: 'Arial, sans-serif',
        },
        heading: {
            fontSize: '3em',
            marginBottom: '20px',
        },
        toastContainer: {
            position: 'absolute',
            top: '20px',
            right: '20px',
        }
    };

    return (
        <div style={styles.container}>
            <h1 style={styles.heading}>Trang Chủ</h1>
            <ToastContainer style={styles.toastContainer} />
        </div>
    );
};

export default Home;
