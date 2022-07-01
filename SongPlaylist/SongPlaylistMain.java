package SongPlaylist;

import java.util.*;

public class SongPlaylistMain {

    private static ArrayList<Album> albums = new ArrayList<>();

    public static void main(String[] args) {

        Album album = new Album("Remastered", "Metallica");

        album.addSong("Nothing Else Matters", 6.2);
        album.addSong("Enter Sandman", 5.3);
        album.addSong("Sad But True", 5.2);
        album.addSong("The Unforgiven", 6.2);
        album.addSong("Don't Tread On Me", 4.0);
        albums.add(album);

        album = new Album("Bozkırın Tezenesi", "Neşet Ertaş");

        album.addSong("Neredesin Sen", 5.2);
        album.addSong("Acem Kızı", 3.5);
        album.addSong("Gönül Dağı", 5.0);
        album.addSong("Ahirim Sensin", 8.2);
        album.addSong("Böyle Olur mu", 4.1);
        albums.add(album);

        LinkedList<Song> neşetBaba = new LinkedList<>();

        albums.get(0).addSongToPlaylist("Neredesin Sen", neşetBaba);
        albums.get(0).addSongToPlaylist("Acem Kızı", neşetBaba);
        albums.get(0).addSongToPlaylist("Gönül Dağı", neşetBaba);
        albums.get(1).addSongToPlaylist("Ahirim Sensin", neşetBaba);
        albums.get(1).addSongToPlaylist("Böyle Olur mu", neşetBaba);

        play(neşetBaba);
    }

    private static void play(LinkedList<Song> playlist) {
        Scanner scan = new Scanner(System.in);
        boolean quit = false;
        boolean forward = true;
        ListIterator<Song> listIterator = playlist.listIterator();

        if (playlist.size() == 0) {
            System.out.println("This playlist have no song!");
        } else {
            System.out.println("Now playing: " + listIterator.next().toString());
            menu();
        }

        while (!quit) {
            int action = scan.nextInt();
            scan.nextLine();

            switch (action) {
                case 0:
                    System.out.println("Playlist complete!");
                    quit = true;
                    break;

                case 1:
                    if (!forward) {
                        if (listIterator.hasNext()) {
                            listIterator.next();
                        }
                        forward = true;
                    }
                    if (listIterator.hasNext()) {
                        System.out.println("Now playing: " + listIterator.next().toString());
                    } else {
                        System.out.println("No song available, reached the end of the playlist!");
                        forward = false;
                    }
                    break;

                case 2:
                    if (forward) {
                        if (listIterator.hasPrevious()) {
                            listIterator.previous();
                        }
                        forward = false;
                    }
                    if (listIterator.hasPrevious()) {
                        System.out.println("Now playing: " + listIterator.previous().toString());
                    } else {
                        System.out.println("We are the first song!");
                        forward = false;
                    }

                    break;
                case 3:
                    if (forward) {
                        if (listIterator.hasPrevious()) {
                            System.out.println("Now playing " + listIterator.previous().toString());
                            forward = false;
                        } else {
                            System.out.println("We are at the start of the list!");
                        }
                    } else {
                        if (listIterator.hasNext()) {
                            System.out.println("Now playing " + listIterator.next().toString());
                            forward = true;
                        } else {
                            System.out.println("We have reached to the end of the list!");
                        }
                    }
                    break;

                case 4:
                    printList(playlist);
                    break;

                case 5:
                    menu();
                    break;

                case 6:
                    if (playlist.size() > 0) {
                        listIterator.remove();
                        if (listIterator.hasNext()) {
                            System.out.println("Now playing " + listIterator.next().toString());
                            forward = true;
                        } else {
                            if (listIterator.hasPrevious())
                                System.out.println("Now playing " + listIterator.previous().toString());
                        }
                    }
            }
        }
    }

    private static void menu() {
        System.out.println("Available options\n press");
        System.out.println(
                "0 - for quit\n" +
                        "1 - for play next song\n" +
                        "2 - for play previous song\n" +
                        "3 - for replay the current song\n" +
                        "4 - for list of all songs\n" +
                        "5 - for print all available options\n+" +
                        "6 - for deleting current song");
    }

    private static void printList(LinkedList<Song> playlist) {
        Iterator<Song> iterator = playlist.iterator();
        System.out.println("--------------------------");

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        System.out.println("--------------------------");
    }
}
