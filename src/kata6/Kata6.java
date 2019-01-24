package kata6;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Iterator;

public class Kata6 {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        File file = new File("/Program Files");
        BasicFileAttributes attr = Files.readAttributes(file.toPath(), BasicFileAttributes.class, LinkOption.NOFOLLOW_LINKS);
        boolean name = attr.isOther();
        System.out.println(name);
        //Iterator<Integer> lengths = megabytes(lengthsOf(iteratorOf(file.listFiles())));
        //iterate(file.listFiles());
    }

    private static Iterator<Integer> megabytes(Iterator<Long> iterator) {
        return new Iterator<Integer>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public Integer next() {
                return (int) (iterator.next() / (1024*1024));
            }
        };
    }

    private static Iterator<Long> lengthsOf(Iterator<File> iterator) {
        return new Iterator<Long>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public Long next() {
                return iterator.next().length();
            }
        };

    }

    private static <T> Iterator<T> iteratorOf(T[] items) {
        return new Iterator<T>() {
            private int index = 0;
            @Override
            public boolean hasNext() {
                return index < items.length;
            }

            @Override
            public T next() {
                return items[index++];
            }
        };
    }
    private static void iterate(File[] file){
        Iterator<File> iter = iteratorOf(file);
        while (iter.hasNext()) {
            File f = iter.next();
            if (f.isFile() && !f.isHidden()) {
                System.out.println(f.getAbsolutePath());
            } else if(f.isDirectory() && !f.isHidden()) {
                iterate(f.listFiles());
            }
        }
    }

}