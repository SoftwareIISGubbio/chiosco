rsync -av . edoardo@kili:/var/apache2/kili/www/edoardo/chiosco

rsync -av --delete . edoardo@kili:/var/apache2/kili/www/edoardo/chiosco

mount -t 9p -o trans=virtio share /mnt
ffmpeg -i italianoStoria.mp4 -vf scale=-1:540 -vcodec libx264 -crf 28 italianoStoria_r.mp4