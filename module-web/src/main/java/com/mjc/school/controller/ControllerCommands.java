package com.mjc.school.controller;

import com.mjc.school.service.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class ControllerCommands {
    private final BaseController<NewsDtoRequest, NewsDtoResponse, Long> newsController;
    private final BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> authorController;
    private final BaseController<TagDtoRequest, TagDtoResponse, Long> tagController;
    String menuText= "Choose command:" + "\n"
            + "1- Get all authors " + "\n"
            + "2-Get all news" + "\n"
            + "3-Create author" + "\n"
            + "4-Create news" + "\n"
            + "5-Delete author" + "\n"
            + "6-Delete news" + "\n"
            + "7-Get author by ID" + "\n"
            + "8-Get news by ID" + "\n"
            + "9-Update author" + "\n"
            + "10-Update news" + "\n"
            + "11-Get author by news Id" + "\n"
            + "12-Get all tags" + "\n"
            + "13-Create tag" + "\n"
            + "14-Get tag by Id" + "\n"
            + "15-Update tag" + "\n"
            + "16-Delete tag" + "\n"
            + "17-Get tags by news Id" + "\n"
            + "18-Get news by parameters" + "\n"
            + "0-Exit";

    @Autowired
    public ControllerCommands(BaseController<NewsDtoRequest, NewsDtoResponse, Long> newsController, BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> authorController,BaseController<TagDtoRequest,TagDtoResponse,Long> tagController) {
        this.newsController = newsController;
        this.authorController = authorController;
        this.tagController=tagController;
    }
    public void execute() {
        Scanner scanner1 = new Scanner(System.in);
        while (true) {
            System.out.println();
            int numberOfCommand = scanner1.nextInt();
            switch (numberOfCommand) {
                case 1 ->{authorController.readAll().forEach(System.out::println);
                }

                case 2 ->{newsController.readAll().forEach(System.out::println);
                }

                case 3 ->{
                boolean isTrue = false;
                while (!isTrue) {
                    try {
                        Scanner scanner = new Scanner(System.in);
                        System.out.println("Write author name:");
                        String tmpName = scanner.nextLine();
                        System.out.println(authorController.create(new AuthorDtoRequest((long) authorController.readAll().size() + 1, tmpName)));
                        isTrue = true;
                    } catch (RuntimeException e) {
                        throw new RuntimeException("Author name is invalid");
                    }
                }}

                case 4 ->{boolean isTrue = false;
                    while (!isTrue) {
                        try {
                            Scanner scanner = new Scanner(System.in);
                            System.out.println("Write News title:");
                            String tmpTitle = scanner.nextLine();
                            System.out.println("Write News content:");
                            String tmpContent = scanner.nextLine();
                            System.out.println("Write News author id:");
                            Long tmpAuthor = scanner.nextLong();
                            System.out.println(newsController.create(new NewsDtoRequest((long)(newsController.readAll().size()+1),tmpTitle,tmpContent,tmpAuthor)));
                            isTrue = true;
                        }
                        catch (Exception e){
                            throw new RuntimeException("News id is invalid");
                        }
                    }}

                case 5 ->{        System.out.println("Write Author id:");
                    boolean isTrue = false;
                    while (!isTrue) {
                        try {
                            Scanner scanner = new Scanner(System.in);
                            Long authorId = scanner.nextLong();
                            System.out.println(authorController.deleteById(authorId - 1));
                            isTrue = true;
                        } catch (Exception e) {
                            throw new RuntimeException("Author id is invalid");
                        }
                    }}
                case 6 ->{
                    System.out.println("Write News id:");
                    boolean isTrue = false;
                    while (!isTrue) {

                        try {
                            Scanner scanner = new Scanner(System.in);
                            System.out.println(newsController.deleteById(scanner.nextLong() - 1));
                            isTrue = true;
                        } catch (RuntimeException e) {
                            throw new RuntimeException("News id is invalid");
                        }
                    }
                }
                case 7 ->{System.out.println("Write Author id:");
                    boolean isTrue = false;
                    while (!isTrue) {
                        try {
                            Scanner scanner = new Scanner(System.in);
                            System.out.println(authorController.readById(scanner.nextLong()));
                            isTrue = true;
                        } catch (RuntimeException e) {
                            throw new RuntimeException("Author id is invalid");
                        }
                    }

                }

                case 8 ->{        System.out.println("Write News id:");
                    boolean isTrue = false;
                    while (!isTrue) {
                        try {
                            Scanner scanner = new Scanner(System.in);
                            System.out.println(newsController.readById(scanner.nextLong()));
                            isTrue = true;
                        } catch (RuntimeException e) {
                            throw new RuntimeException("News id is invalid");
                        }
                    }

                }
                case 9 ->{        boolean isTrue = false;
                    while (!isTrue) {
                        try {
                            Scanner scanner = new Scanner(System.in);
                            System.out.println("Write author id:");
                            Long id = Long.parseLong(scanner.nextLine());
                            System.out.println(authorController.readById(id).toString());
                            System.out.println("Write author name:");
                            String authorName = scanner.nextLine();
                            System.out.println(authorController.update(new AuthorDtoRequest(id, authorName)));
                            isTrue = true;
                        } catch (RuntimeException e) {
                            throw new RuntimeException("Author is invalid");
                        }
                    }
                }

                case 10 ->{boolean isTrue = false;
                    while (!isTrue) {
                        try {
                            Scanner scanner = new Scanner(System.in);
                            System.out.println("Write News id:");
                            Long id = Long.parseLong(scanner.nextLine());
                            System.out.println(newsController.readById(id));
                            System.out.println("Write News title:");
                            String tmpTitle = scanner.nextLine();
                            System.out.println("Write News content:");
                            String tmpContent = scanner.nextLine();
                            System.out.println("Write News author id:");
                            Long tmpAuthor = scanner.nextLong();
                            System.out.println(newsController.update(new NewsDtoRequest(id, tmpTitle, tmpContent, tmpAuthor)));
                            isTrue = true;
                        } catch (RuntimeException e) {
                            throw new RuntimeException("News is invalid");
                        }
                    }
                }

                case 11 ->{boolean isTrue = false;
                    while (!isTrue) {
                        try {
                            Scanner scanner = new Scanner(System.in);
                            System.out.println("Write News id:");
                            Long id = Long.parseLong(scanner.nextLine());
                            NewsDtoResponse news =  newsController.readById(id);
                            System.out.println(authorController.readById(news.getAuthorId()));
                            isTrue = true;
                        } catch (RuntimeException e) {
                            throw new RuntimeException("News is invalid");
                        }
                    }
                }

                case 12 ->{tagController.readAll().forEach(System.out::println);
                }

                case 13 ->{boolean isTrue = false;
                    while (!isTrue) {
                        try {
                            Scanner scanner = new Scanner(System.in);
                            System.out.println("Write tag name:");
                            String tmpName = scanner.nextLine();
                            System.out.println(tagController.create(new TagDtoRequest((long) tagController.readAll().size() + 1, tmpName)));
                            isTrue = true;
                        } catch (RuntimeException e) {
                            throw new RuntimeException("Author name is invalid");
                        }
                    }}

                case 14 ->{System.out.println("Write Author id:");
                boolean isTrue = false;
                while (!isTrue) {
                    try {
                        Scanner scanner = new Scanner(System.in);
                        System.out.println(tagController.readById(scanner.nextLong()));
                        isTrue = true;
                    } catch (RuntimeException e) {
                        throw new RuntimeException("Tag id is invalid");
                    }
                }}
                case 15 -> {
                    boolean isTrue = false;
                    while (!isTrue) {
                        try {
                            Scanner scanner = new Scanner(System.in);
                            System.out.println("Write tag id:");
                            Long id = Long.parseLong(scanner.nextLine());
                            System.out.println(tagController.readById(id).toString());
                            System.out.println("Write tag name:");
                            String tagName = scanner.nextLine();
                            System.out.println(tagController.update(new TagDtoRequest(id, tagName)));
                            isTrue = true;
                        } catch (RuntimeException e) {
                            throw new RuntimeException("Tag is invalid");
                        }
                    }
                }
                case 16 ->{        System.out.println("Write tag id:");
                    boolean isTrue = false;
                    while (!isTrue) {
                        try {
                            Scanner scanner = new Scanner(System.in);
                            Long tagId = scanner.nextLong();
                            System.out.println(tagController.deleteById(tagId - 1));
                            isTrue = true;
                        } catch (Exception e) {
                            throw new RuntimeException("Tag id is invalid");
                        }
                    }}

                case 17 ->{System.out.println("Write news id:");
                    boolean isTrue = false;
                    while (!isTrue) {
                        try {
                            Scanner scanner = new Scanner(System.in);
                            Long newsId = scanner.nextLong();
                            List<TagDtoResponse> taglist = newsController.readById(newsId).getTagDtoResponseList();
                            for (TagDtoResponse tagDtoResponse : taglist) {
                                System.out.println(tagDtoResponse.getName());
                            }
                            isTrue = true;
                        } catch (Exception e) {
                            throw new RuntimeException("News id is invalid");
                        }
                    }}

                case 18 -> {
                    //by tag names, tag ids, author name, title, content
                    System.out.println("Write tag name:");
                    List<NewsDtoResponse> result = new ArrayList<>();
                    Scanner scanner = new Scanner(System.in);
                    String tagName = scanner.nextLine();
                    if(tagName!=null){
                        TagDtoResponse tagSearched = tagController.readAll().stream().filter(tagDtoResponse -> tagDtoResponse.getName().equals(tagName)).findFirst().orElse(null);
                        result.addAll(newsController.readAll().stream().filter(newsDtoResponse -> newsDtoResponse.getTagDtoResponseList().contains(tagSearched)).toList());
                    }

                    System.out.println("Write tag id:");
                    Long tagId = scanner.nextLong();
                    if(tagId!=null){
                        TagDtoResponse tagSearched = tagController.readAll().stream().filter(tagDtoResponse -> tagDtoResponse.getId().equals(tagId)).findFirst().orElse(null);
                        result.addAll(newsController.readAll().stream().filter(newsDtoResponse -> newsDtoResponse.getTagDtoResponseList().contains(tagSearched)).toList());
                    }

                    System.out.println("Write author name:");
                    String authorName = scanner.nextLine();
                    if(authorName!=null){
                        AuthorDtoResponse authorSearched = authorController.readAll().stream().filter(authorDtoResponse -> authorDtoResponse.getName().equals(authorName)).findFirst().orElse(null);
                        result.addAll(newsController.readAll().stream().filter(newsDtoResponse -> newsDtoResponse.getAuthorId().equals(authorSearched.getId())).toList());
                    }

                    System.out.println("Write title:");
                    String title = scanner.nextLine();
                    if(title!=null) {
                        result.addAll(newsController.readAll().stream().filter(newsDtoResponse -> newsDtoResponse.getTitle().equals(title)).toList());
                    }

                    System.out.println("Write content:");
                    String content = scanner.nextLine();
                    if(content!=null) {
                        result.addAll(newsController.readAll().stream().filter(newsDtoResponse -> newsDtoResponse.getContent().equals(content)).toList());
                    }
                    result.forEach(System.out::println);
                }

                case 0 -> System.exit(0);
            }
        }
    }






}
