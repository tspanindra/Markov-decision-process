package markov.decision.process;

import java.io.*;


public class EnvironmentReader {
    BufferedReader br;
    File environmentFile;
    Double[][] environment;
    int length, width;
    int startX, startY;

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
    
    public Double[][] getEnvironment() {
        return environment;
    }

    public void setEnvironment(Double[][] environment) {
        this.environment = environment;
    }
    


    public EnvironmentReader(File file) throws FileNotFoundException, IOException {
        init(file);
    }

    private void init(File file) throws FileNotFoundException, IOException {
        this.environmentFile = file;
        br = new BufferedReader(new FileReader(environmentFile));
        getDimensions();
        read();     
    }

private void read() throws IOException {
    br = new BufferedReader(new FileReader(environmentFile));
    environment = new Double[length][width];
    for(int i = 0 ; i < length ; i ++)
    {
        environment[i] = new Double[width];
        String line = br.readLine();
        for(int j = 0 ; j < width ; j ++)
        {
            char character = line.charAt(j);
            if(character == '%')
            {
                environment[i][j] = Double.MIN_VALUE;
            }
            else if(character == ' ')
            {
                environment[i][j] = -0.04;
            }
            else if(character == 'R')
            {
                environment[i][j] = 1.0;
            }
            else if(character == 'P')
            {
                environment[i][j] = -1.0;
            }
            else if(character == 'S')
            {
                environment[i][j] = -0.04;
                startX = j;
                startY = i;
            }
        }
    }

}
    private void getDimensions() throws IOException {        
        String line = br.readLine();
        width = line.length();
        int i = 1;
        while(br.readLine() != null)
        {
            i ++;
        }
        length = i;
    }


}
