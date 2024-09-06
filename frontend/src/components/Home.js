import React from 'react';
import { Typography, Box, Button } from '@mui/material';
import { Link } from 'react-router-dom';

function Home() {
  return (
    <Box sx={{ 
      marginTop: 8, 
      display: 'flex', 
      flexDirection: 'column', 
      alignItems: 'center' 
    }}>
      <Typography variant="h2" component="h1" gutterBottom>
        Welcome to Succotash
      </Typography>
      <Typography variant="h5" component="h2" gutterBottom>
        Your Spring Boot and React Application
      </Typography>
      <Box sx={{ mt: 4 }}>
        <Button variant="contained" component={Link} to="/signup" sx={{ mr: 2 }}>
          Sign Up
        </Button>
        <Button variant="outlined" component={Link} to="/login">
          Login
        </Button>
      </Box>
    </Box>
  );
}

export default Home;