import { Link } from "react-router-dom";
import Container from '@mui/material/Container';

const Home = () => {
  return (
    <>
      <Container fixed>
        <h1>
          Hi there
        </h1>
        <Link to="chat" > CHAT </Link>
      </Container>

    </>
  )
}

export default Home;