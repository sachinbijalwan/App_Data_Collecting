clc;
close all;
directory='/home/sachin/Photos/all';  //directory from which image would be taken for
files= dir(directory);
min_r=255;
min_b=255;
min_g=255;
max_r=0;
max_b=0;
max_g=0;
si=size(files);
for i=3:si
    image=files(i).name
    Noise=imread(strcat(directory,'/',image));
    I(:,:,1)=medfilt2(Noise(:,:,1));
    I(:,:,2)=medfilt2(Noise(:,:,2));
    I(:,:,3)=medfilt2(Noise(:,:,3));
    red=I(:,:,1);
    green=I(:,:,2);
    blue=I(:,:,3);
   out = red > 41 & red <164 & green<162 & green>49 & blue <150 & blue > 36;   //these values are calculated on the data contained in the roads
   name=image;
   col=768/4;
   out(:,1:3*col)=0;
   final_out=zeros(1024,768,3);
   for i=1:1024
       for j=1:768
               if(out(i,j)==0)
                   % final_out(i,j,1)=Noise(i,j,:);		
                   I(i,j,:)=0;
               end
       end
   end
    imwrite(I,strcat('/home/sachin/Photos/separated_coloured/',name),'jpg');    //images are being written to the next directory here
   % imshow(Noise);								//and what is being written is the segmented colourful image of road
   %{
 d=impixel(I,720,514);  					//this is for calculating the limits within which red,green and blue colour would
    c=r+(i-2);							//lie for a road
    r(i-2)=mat2cell(d(1),1);
    g(i-2)=mat2cell(d(2),1);
    b(i-2)=mat2cell(d(3),1);
    if(min_r > d(1))
        min_r=d(1);
    end;
    if(max_r<d(1))
        max_r=d(1);
    end;
   if(min_g > d(2))
        min_g=d(2);
    end;
    if(max_g<d(2))
        max_g=d(2);
    end;
     if(min_b>d(3))
        min_b=d(3);
    end;
    if(max_b<d(3))
        max_b=d(3);
    end; 
    %}
    
end
 
