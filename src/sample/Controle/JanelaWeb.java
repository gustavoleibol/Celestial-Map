package sample.Controle;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Shadow;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import sample.Animacoes.ZoomIn;
import sample.Animacoes.ZoomInDown;
import sample.Modelo.JDBCUsuariosDAO;

public class JanelaWeb {

    @FXML
    private WebView view;
    @FXML
    private Label text;
    private ColorAdjust colorAdjust;

    public void initialize(){
        text.setVisible(false);
        text.setDisable(true);
        WebEngine eg = view.getEngine();
        if(JDBCUsuariosDAO.getInstance().getHubble() == 1) { //Andromeda
            eg.load("https://www.spacetelescope.org/images/heic1502a/zoomable/");
            text.setText("This image, captured with the NASA/ESA Hubble Space Telescope, is the largest and sharpest image ever taken of the Andromeda galaxy — otherwise known as M31.\n" +
                    "\n" +
                    "This is a cropped version of the full image and has 1.5 billion pixels. You would need more than 600 HD television screens to display the whole image.\n" +
                    "\n" +
                    "It is the biggest Hubble image ever released and shows over 100 million stars and thousands of star clusters embedded in a section of the galaxy’s pancake-shaped disc stretching across over 40 000 light-years.\n\nCredit: " +
                    "NASA, ESA, J. Dalcanton (University of Washington, USA), B. F. Williams (University of Washington, USA), L. C. Johnson (University of Washington, USA), the PHAT team, and R. Gendler.");
        }
        else if(JDBCUsuariosDAO.getInstance().getHubble() == 2){ //Westerlund 2
            eg.load("https://www.spacetelescope.org/images/heic1509a/zoomable/");
            text.setText("This NASA/ESA Hubble Space Telescope image of the cluster Westerlund 2 and its surroundings has been released to celebrate Hubble’s 25th year in orbit and a quarter of a century of new discoveries, stunning images and outstanding science.\n" +
                    "\n" +
                    "The image’s central region, containing the star cluster, blends visible-light data taken by the Advanced Camera for Surveys and near-infrared exposures taken by the Wide Field Camera 3. The surrounding region is composed of visible-light observations taken by the Advanced Camera for Surveys.\n\nCredit: " +
                    "NASA, ESA, the Hubble Heritage Team (STScI/AURA), A. Nota (ESA/STScI), and the Westerlund 2 Science Team");
        }
        else if(JDBCUsuariosDAO.getInstance().getHubble() == 3){ //Crab Nebula
            eg.load("https://www.spacetelescope.org/images/heic0515a/zoomable/");
            text.setText("This Hubble image gives the most detailed view of the entire Crab Nebula ever. The Crab is among the most interesting and well studied objects in astronomy.\n" +
                    "\n" +
                    "This image is the largest image ever taken with Hubble's WFPC2 camera. It was assembled from 24 individual exposures taken with the NASA/ESA Hubble Space Telescope and is the highest resolution image of the entire Crab Nebula ever made.\n" +
                    "\n" +
                    "Credit: " +
                    "NASA, ESA and Allison Loll/Jeff Hester (Arizona State University). Acknowledgement: Davide De Martin (ESA/Hubble)");
        }
        else if(JDBCUsuariosDAO.getInstance().getHubble() == 4){ //Carina Nebula
            eg.load("https://www.spacetelescope.org/images/heic0910e/zoomable/");
            text.setText("Composed of gas and dust, the pictured pillar resides in a tempestuous stellar nursery called the Carina Nebula, located 7500 light-years away in the southern constellation of Carina.\n" +
                    "\n" +
                    "Taken in visible light, the image shows the tip of the three-light-year-long pillar, bathed in the glow of light from hot, massive stars off the top of the image. Scorching radiation and fast winds (streams of charged particles) from these stars are sculpting the pillar and causing new stars to form within it. Streamers of gas and dust can be seen flowing off the top of the structure.\n" +
                    "\n" +
                    "Hubble's Wide Field Camera 3 observed the Carina Nebula on 24-30 July 2009. WFC3 was installed aboard Hubble in May 2009 during Servicing Mission 4. The composite image was made from filters that isolate emission from iron, magnesium, oxygen, hydrogen and sulphur.\n" +
                    "\n" +
                    "These Hubble observations of the Carina Nebula are part of the Hubble Servicing Mission 4 Early Release Observations.\n" +
                    "\n" +
                    "Credit: " +
                    "NASA, ESA and the Hubble SM4 ERO Team");
        }
        else if(JDBCUsuariosDAO.getInstance().getHubble() == 5){ //Deep Field
            eg.load("https://www.spacetelescope.org/images/heic0611b/zoomable/");
            text.setText("This view of nearly 10,000 galaxies is called the Hubble Ultra Deep Field. The snapshot includes galaxies of various ages, sizes, shapes, and colours. The smallest, reddest galaxies, about 100, may be among the most distant known, existing when the universe was just 800 million years old. The nearest galaxies - the larger, brighter, well-defined spirals and ellipticals - thrived about 1 billion years ago, when the cosmos was 13 billion years old.\n" +
                    "\n" +
                    "The image required 800 exposures taken over the course of 400 Hubble orbits around Earth. The total amount of exposure time was 11.3 days, taken between Sept. 24, 2003 and Jan. 16, 2004.\n" +
                    "\n" +
                    "Credit: " +
                    "NASA, ESA, and S. Beckwith (STScI) and the HUDF Team");
        }
        else if(JDBCUsuariosDAO.getInstance().getHubble() == 6){ //Pillars of Creation
            eg.load("https://www.spacetelescope.org/images/heic1501a/zoomable/");
            text.setText("The NASA/ESA Hubble Space Telescope has revisited one of its most iconic and popular images: the Eagle Nebula’s Pillars of Creation. This image shows the pillars as seen in visible light, capturing the multi-coloured glow of gas clouds, wispy tendrils of dark cosmic dust, and the rust-coloured elephants’ trunks of the nebula’s famous pillars.\n" +
                    "\n" +
                    "The dust and gas in the pillars is seared by the intense radiation from young stars and eroded by strong winds from massive nearby stars. With these new images comes better contrast and a clearer view for astronomers to study how the structure of the pillars is changing over time.\n" +
                    "\n" +
                    "Credit: " +
                    "NASA, ESA/Hubble and the Hubble Heritage Team");
        }
        else if(JDBCUsuariosDAO.getInstance().getHubble() == 7){ //Sombrero Galaxy
            eg.load("https://www.spacetelescope.org/images/opo0328a/zoomable/");
            text.setText("NASA/ESA Hubble Space Telescope has trained its razor-sharp eye on one of the universe's most stately and photogenic galaxies, the Sombrero galaxy, Messier 104 (M104). The galaxy's hallmark is a brilliant white, bulbous core encircled by the thick dust lanes comprising the spiral structure of the galaxy. As seen from Earth, the galaxy is tilted nearly edge-on. We view it from just six degrees north of its equatorial plane. This brilliant galaxy was named the Sombrero because of its resemblance to the broad rim and high-topped Mexican hat.\n" +
                    "\n" +
                    "At a relatively bright magnitude of +8, M104 is just beyond the limit of naked-eye visibility and is easily seen through small telescopes. The Sombrero lies at the southern edge of the rich Virgo cluster of galaxies and is one of the most massive objects in that group, equivalent to 800 billion suns. The galaxy is 50,000 light-years across and is located 30 million light-years from Earth.\n" +
                    "\n" +
                    "Credit: " +
                    "NASA/ESA and The Hubble Heritage Team (STScI/AURA)");
        }
        else if(JDBCUsuariosDAO.getInstance().getHubble() == 8){ //Helix Nebula
            eg.load("https://www.spacetelescope.org/images/opo0432d/zoomable/");
            text.setText("This composite image is a view of the colorful Helix Nebula taken with the Advanced Camera for Surveys aboard NASA/ESA Hubble Space Telescope and the Mosaic II Camera on the 4-meter telescope at Cerro Tololo Inter-American Observatory in Chile. The object is so large that both telescopes were needed to capture a complete view. The Helix is a planetary nebula, the glowing gaseous envelope expelled by a dying, sun-like star. The Helix resembles a simple doughnut as seen from Earth. But looks can be deceiving. New evidence suggests that the Helix consists of two gaseous disks nearly perpendicular to each other.\n" +
                    "\n" +
                    "Credit: " +
                    "NASA, ESA, C.R. O'Dell (Vanderbilt University), and M. Meixner, P. McCullough, and G. Bacon ( Space Telescope Science Institute)");
        }
        else if(JDBCUsuariosDAO.getInstance().getHubble() == 9){ //Cassiopeia A
            eg.load("https://www.spacetelescope.org/images/heic0609a/zoomable/");
            text.setText("A new image taken with the NASA/ESA Hubble Space Telescope provides a detailed look at the tattered remains of a supernova explosion known as Cassiopeia A (Cas A). It is the youngest known remnant from a supernova explosion in the Milky Way. The new Hubble image shows the complex and intricate structure of the star's shattered fragments.\n" +
                    "\n" +
                    "Credit: " +
                    "NASA, ESA, and the Hubble Heritage (STScI/AURA)-ESA/Hubble Collaboration. Acknowledgement: Robert A. Fesen (Dartmouth College, USA) and James Long (ESA/Hubble)");
        }
        else if(JDBCUsuariosDAO.getInstance().getHubble() == 10){ //Supernova
            eg.load("https://www.spacetelescope.org/images/potw1401a/zoomable/");
            text.setText("Floating at the centre of this new Hubble image is a lidless purple eye, staring back at us through space. This ethereal object, known officially as [SBW2007] 1 but sometimes nicknamed SBW1, is a nebula with a giant star at its centre. The star was originally twenty times more massive than our Sun, and is now encased in a swirling ring of purple gas, the remains of the distant era when it cast off its outer layers via violent pulsations and winds.\n" +
                    "\n" +
                    "But the star is not just any star; scientists say that it is destined to go supernova! 26 years ago, another star with striking similarities went supernova — SN 1987A. Early Hubble images of SN 1987A show eerie similarities to SBW1. Both stars had identical rings of the same size and age, which were travelling at similar speeds; both were located in similar HII regions; and they had the same brightness. In this way SBW1 is a snapshot of SN1987a's appearance before it exploded, and unsurprisingly, astronomers love studying them together.\n" +
                    "\n" +
                    "At a distance of more than 20 000 light-years it will be safe to watch when the supernova goes off. If we are very lucky it may happen in our own lifetimes...\n" +
                    "\n" +
                    "Versions of this image were entered into the Hubble's Hidden Treasures image processing competition by contestants Nick Rose and Steve Byrne.\n" +
                    "\n" +
                    "Credit: " +
                    "ESA/Hubble & NASA " +
                    "Acknowledgements: Nick Rose/Steve Byrne");
        }
    }

    @FXML
    private void Text(){
        if(text.isVisible() == false) {
            new ZoomIn(text).play();
            colorAdjust = new ColorAdjust();
            colorAdjust.setBrightness(-0.5);
            view.setEffect(colorAdjust);
            new ZoomInDown(text);
            text.setVisible(true);
            text.setDisable(false);
        }
        else{
            colorAdjust = new ColorAdjust();
            colorAdjust.setBrightness(0);
            view.setEffect(colorAdjust);
            text.setVisible(false);
            text.setDisable(true);
        }
    }
}
