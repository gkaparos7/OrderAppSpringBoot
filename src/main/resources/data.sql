INSERT INTO categories (category_name, photo_url) VALUES ('Judo', 'https://media.gettyimages.com/id/1151765319/photo/male-judo-players-competing-during-match.jpg?s=2048x2048&w=gi&k=20&c=POWRETcWpxAFwq_k3TZKkuTbnSd-XCw8DNbB3UyiTBE=');

INSERT INTO categories (category_name, photo_url) VALUES ('Aikido', 'https://media.gettyimages.com/id/95692353/photo/men-practicing-aikido-martial-arts.jpg?s=2048x2048&w=gi&k=20&c=KHzKkyx03Wt6uj8VUmf-BAop1NlMLQyWQtSofNe4Syc=');

INSERT INTO categories (category_name, photo_url) VALUES ('Karate', 'https://media.gettyimages.com/id/612395074/photo/cute-little-boy-taking-karate.jpg?s=2048x2048&w=gi&k=20&c=tFOpzaIQI-yranseIu6zv16Ioxy0-41ZIglhmKv1neE=');

INSERT INTO subcategories (subcategory_name, photo_url, category_id) VALUES
                                                                         ('Gis', 'https://www.budokas.gr/wp-content/uploads/2022/02/006430_matsuru_ijf_champion_gold_blauw_met_ijf_logo_1_12.jpg', (SELECT id FROM categories WHERE category_name = 'Judo')),
                                                                         ('Pants', 'https://www.matsuru.com/pub/media/catalog/product/cache/7d361c3e738cad9ccd3190ce570ef8b4/0/0/0047_super_judo_pantalon_met_ijf_logo.jpg', (SELECT id FROM categories WHERE category_name = 'Judo')),
                                                                         ('Training Equipment', 'https://www.matsuru.com/pub/media/catalog/product/cache/7d361c3e738cad9ccd3190ce570ef8b4/0/3/034323.jpg', (SELECT id FROM categories WHERE category_name = 'Judo')),
                                                                         ('Belts', 'https://www.matsuru.com/pub/media/catalog/product/cache/c9127b3a1b0fc7cc580cf429fd7e8a97/i/j/ijf_budoband_zwart_met_ijf_logo_1.jpg', (SELECT id FROM categories WHERE category_name = 'Judo')),
                                                                         ('Bags', 'https://www.matsuru.com/pub/media/catalog/product/cache/c9127b3a1b0fc7cc580cf429fd7e8a97/c/a/camouflage_tas_judo_zwart-grijs_lr.jpg', (SELECT id FROM categories WHERE category_name = 'Judo'));

INSERT INTO subcategories (subcategory_name, photo_url, category_id) VALUES
                                                                         ('Gis', 'https://www.budokas.gr/wp-content/uploads/2022/02/006430_matsuru_ijf_champion_gold_blauw_met_ijf_logo_1_12.jpg', (SELECT id FROM categories WHERE category_name = 'Aikido')),
                                                                         ('Pants', 'https://www.matsuru.com/pub/media/catalog/product/cache/7d361c3e738cad9ccd3190ce570ef8b4/0/0/0047_super_judo_pantalon_met_ijf_logo.jpg', (SELECT id FROM categories WHERE category_name = 'Aikido')),
                                                                         ('Training Equipment', 'https://www.matsuru.com/pub/media/catalog/product/cache/7d361c3e738cad9ccd3190ce570ef8b4/0/3/034323.jpg', (SELECT id FROM categories WHERE category_name = 'Aikido')),
                                                                         ('Belts', 'https://www.matsuru.com/pub/media/catalog/product/cache/c9127b3a1b0fc7cc580cf429fd7e8a97/i/j/ijf_budoband_zwart_met_ijf_logo_1.jpg', (SELECT id FROM categories WHERE category_name = 'Aikido')),
                                                                         ('Bags', 'https://www.matsuru.com/pub/media/catalog/product/cache/c9127b3a1b0fc7cc580cf429fd7e8a97/c/a/camouflage_tas_judo_zwart-grijs_lr.jpg', (SELECT id FROM categories WHERE category_name = 'Aikido'));

INSERT INTO subcategories (subcategory_name, photo_url, category_id) VALUES
                                                                        ('Gis', 'https://www.budokas.gr/wp-content/uploads/2022/02/006430_matsuru_ijf_champion_gold_blauw_met_ijf_logo_1_12.jpg', (SELECT id FROM categories WHERE category_name = 'Karate')),
                                                                        ('Pants', 'https://www.matsuru.com/pub/media/catalog/product/cache/7d361c3e738cad9ccd3190ce570ef8b4/0/0/0047_super_judo_pantalon_met_ijf_logo.jpg', (SELECT id FROM categories WHERE category_name = 'Karate')),
                                                                        ('Training Equipment', 'https://www.matsuru.com/pub/media/catalog/product/cache/7d361c3e738cad9ccd3190ce570ef8b4/0/3/034323.jpg', (SELECT id FROM categories WHERE category_name = 'Karate')),
                                                                        ('Belts', 'https://www.matsuru.com/pub/media/catalog/product/cache/c9127b3a1b0fc7cc580cf429fd7e8a97/i/j/ijf_budoband_zwart_met_ijf_logo_1.jpg', (SELECT id FROM categories WHERE category_name = 'Karate')),
                                                                        ('Bags', 'https://www.matsuru.com/pub/media/catalog/product/cache/c9127b3a1b0fc7cc580cf429fd7e8a97/c/a/camouflage_tas_judo_zwart-grijs_lr.jpg', (SELECT id FROM categories WHERE category_name = 'Karate'));


INSERT INTO products (product_name, photo_url, subcategory_id)
VALUES ('Semi Competition Gi Pink', 'https://www.matsuru.com/pub/media/catalog/product/cache/7d361c3e738cad9ccd3190ce570ef8b4/0/0/0025_semi_wedstrijd_wit_geborduurd_roze.jpg', 1);

INSERT INTO product_sizes (product_id, size) VALUES
                                                 (LAST_INSERT_ID(), 'SIZE_150'),
                                                 (LAST_INSERT_ID(), 'SIZE_160'),
                                                 (LAST_INSERT_ID(), 'SIZE_170'),
                                                 (LAST_INSERT_ID(), 'SIZE_180'),
                                                 (LAST_INSERT_ID(), 'SIZE_190'),
                                                 (LAST_INSERT_ID(), 'SIZE_200');

INSERT INTO products (product_name, photo_url, subcategory_id)
VALUES ('Setsugi Gi', 'https://www.matsuru.com/pub/media/catalog/product/cache/7d361c3e738cad9ccd3190ce570ef8b4/w/i/wit_setsugi-03.jpg', 1);

INSERT INTO product_sizes (product_id, size) VALUES
                                                 (LAST_INSERT_ID(), 'SIZE_160'),
                                                 (LAST_INSERT_ID(), 'SIZE_170'),
                                                 (LAST_INSERT_ID(), 'SIZE_180'),
                                                 (LAST_INSERT_ID(), 'SIZE_190'),
                                                 (LAST_INSERT_ID(), 'SIZE_200');

INSERT INTO products (product_name, photo_url, subcategory_id)
VALUES ('IJF Approved White Gi', 'https://www.matsuru.com/pub/media/catalog/product/cache/7d361c3e738cad9ccd3190ce570ef8b4/m/a/matsuru_ijf_champion_gold_wit_met_ijf_logo.jpg', 1);

INSERT INTO product_sizes (product_id, size) VALUES
                                                 (LAST_INSERT_ID(), 'SIZE_140'),
                                                 (LAST_INSERT_ID(), 'SIZE_150'),
                                                 (LAST_INSERT_ID(), 'SIZE_160'),
                                                 (LAST_INSERT_ID(), 'SIZE_170'),
                                                 (LAST_INSERT_ID(), 'SIZE_180'),
                                                 (LAST_INSERT_ID(), 'SIZE_190'),
                                                 (LAST_INSERT_ID(), 'SIZE_200');

INSERT INTO products (product_name, photo_url, subcategory_id)
VALUES ('IJF Approved Blue Gi', 'https://www.matsuru.com/pub/media/catalog/product/cache/7d361c3e738cad9ccd3190ce570ef8b4/0/0/006430_matsuru_ijf_champion_gold_blauw_met_ijf_logo_1.jpg', 11);

INSERT INTO product_sizes (product_id, size) VALUES
                                                 (LAST_INSERT_ID(), 'SIZE_150'),
                                                 (LAST_INSERT_ID(), 'SIZE_160'),
                                                 (LAST_INSERT_ID(), 'SIZE_170'),
                                                 (LAST_INSERT_ID(), 'SIZE_180'),
                                                 (LAST_INSERT_ID(), 'SIZE_190'),
                                                 (LAST_INSERT_ID(), 'SIZE_200');

INSERT INTO products (product_name, photo_url, subcategory_id)
VALUES ('Club Gi', 'https://www.matsuru.com/pub/media/catalog/product/cache/7d361c3e738cad9ccd3190ce570ef8b4/0/0/0016_judo_club_met_label_1.jpg', 1);

INSERT INTO product_sizes (product_id, size) VALUES
                                                 (LAST_INSERT_ID(), 'SIZE_110'),
                                                 (LAST_INSERT_ID(), 'SIZE_120'),
                                                 (LAST_INSERT_ID(), 'SIZE_130'),
                                                 (LAST_INSERT_ID(), 'SIZE_140'),
                                                 (LAST_INSERT_ID(), 'SIZE_150'),
                                                 (LAST_INSERT_ID(), 'SIZE_160'),
                                                 (LAST_INSERT_ID(), 'SIZE_170'),
                                                 (LAST_INSERT_ID(), 'SIZE_180'),
                                                 (LAST_INSERT_ID(), 'SIZE_190'),
                                                 (LAST_INSERT_ID(), 'SIZE_200');

INSERT INTO products (product_name, photo_url, subcategory_id)
VALUES ('Club Blue Gi', 'https://www.matsuru.com/pub/media/catalog/product/cache/7d361c3e738cad9ccd3190ce570ef8b4/0/0/0026_junior_blauw.jpg', 1);

INSERT INTO product_sizes (product_id, size) VALUES
                                                 (LAST_INSERT_ID(), 'SIZE_110'),
                                                 (LAST_INSERT_ID(), 'SIZE_120'),
                                                 (LAST_INSERT_ID(), 'SIZE_130'),
                                                 (LAST_INSERT_ID(), 'SIZE_140'),
                                                 (LAST_INSERT_ID(), 'SIZE_150'),
                                                 (LAST_INSERT_ID(), 'SIZE_160'),
                                                 (LAST_INSERT_ID(), 'SIZE_170'),
                                                 (LAST_INSERT_ID(), 'SIZE_180'),
                                                 (LAST_INSERT_ID(), 'SIZE_190'),
                                                 (LAST_INSERT_ID(), 'SIZE_200');

INSERT INTO products (product_name, photo_url, subcategory_id)
VALUES ('Judo Trousers', 'https://www.matsuru.com/pub/media/catalog/product/cache/7d361c3e738cad9ccd3190ce570ef8b4/0/0/0036.jpg', 2);

INSERT INTO product_sizes (product_id, size) VALUES
                                                 (LAST_INSERT_ID(), 'SIZE_110'),
                                                 (LAST_INSERT_ID(), 'SIZE_120'),
                                                 (LAST_INSERT_ID(), 'SIZE_130'),
                                                 (LAST_INSERT_ID(), 'SIZE_140'),
                                                 (LAST_INSERT_ID(), 'SIZE_150'),
                                                 (LAST_INSERT_ID(), 'SIZE_160'),
                                                 (LAST_INSERT_ID(), 'SIZE_170'),
                                                 (LAST_INSERT_ID(), 'SIZE_180'),
                                                 (LAST_INSERT_ID(), 'SIZE_190'),
                                                 (LAST_INSERT_ID(), 'SIZE_200');

INSERT INTO products (product_name, photo_url, subcategory_id)
VALUES ('Super Judo Trousers', 'https://www.matsuru.com/pub/media/catalog/product/cache/7d361c3e738cad9ccd3190ce570ef8b4/0/0/0047_super_judo_pantalon_met_ijf_logo.jpg', 2);

INSERT INTO product_sizes (product_id, size) VALUES
                                                 (LAST_INSERT_ID(), 'SIZE_110'),
                                                 (LAST_INSERT_ID(), 'SIZE_120'),
                                                 (LAST_INSERT_ID(), 'SIZE_130'),
                                                 (LAST_INSERT_ID(), 'SIZE_140'),
                                                 (LAST_INSERT_ID(), 'SIZE_150'),
                                                 (LAST_INSERT_ID(), 'SIZE_160'),
                                                 (LAST_INSERT_ID(), 'SIZE_170'),
                                                 (LAST_INSERT_ID(), 'SIZE_180'),
                                                 (LAST_INSERT_ID(), 'SIZE_190'),
                                                 (LAST_INSERT_ID(), 'SIZE_200');

INSERT INTO products (product_name, photo_url, subcategory_id)
VALUES ('Super Judo Trousers', 'https://www.matsuru.com/pub/media/catalog/product/cache/7d361c3e738cad9ccd3190ce570ef8b4/0/0/0048_super_judo_pantalon_blauwmet_ijf_logo_13.jpg', 2);

INSERT INTO product_sizes (product_id, size) VALUES
                                                 (LAST_INSERT_ID(), 'SIZE_110'),
                                                 (LAST_INSERT_ID(), 'SIZE_120'),
                                                 (LAST_INSERT_ID(), 'SIZE_130'),
                                                 (LAST_INSERT_ID(), 'SIZE_140'),
                                                 (LAST_INSERT_ID(), 'SIZE_150'),
                                                 (LAST_INSERT_ID(), 'SIZE_160'),
                                                 (LAST_INSERT_ID(), 'SIZE_170'),
                                                 (LAST_INSERT_ID(), 'SIZE_180'),
                                                 (LAST_INSERT_ID(), 'SIZE_190'),
                                                 (LAST_INSERT_ID(), 'SIZE_200');

INSERT INTO products (product_name, photo_url, subcategory_id)
VALUES ('Judo headguard black/gold', 'https://www.matsuru.com/pub/media/catalog/product/cache/7d361c3e738cad9ccd3190ce570ef8b4/j/u/judo_headguard_nepopreen.png', 3);

INSERT INTO product_sizes (product_id, size) VALUES
                                                 (LAST_INSERT_ID(), 'SMALL'),
                                                 (LAST_INSERT_ID(), 'MEDIUM'),
                                                 (LAST_INSERT_ID(), 'LARGE');

INSERT INTO products (product_name, photo_url, subcategory_id)
VALUES ('Ankle stockings in black', 'https://www.matsuru.com/pub/media/catalog/product/cache/7d361c3e738cad9ccd3190ce570ef8b4/0/4/049114_enkelkous_zwart_1_12.jpg', 3);

INSERT INTO product_sizes (product_id, size) VALUES
                                                 (LAST_INSERT_ID(), 'SMALL'),
                                                 (LAST_INSERT_ID(), 'MEDIUM'),
                                                 (LAST_INSERT_ID(), 'LARGE');

INSERT INTO products (product_name, photo_url, subcategory_id)
VALUES ('Shin guard', 'https://www.matsuru.com/pub/media/catalog/product/cache/7d361c3e738cad9ccd3190ce570ef8b4/0/4/042802_scheenbeschermer.jpg', 3);

INSERT INTO product_sizes (product_id, size) VALUES
                                                 (LAST_INSERT_ID(), 'SMALL'),
                                                 (LAST_INSERT_ID(), 'MEDIUM'),
                                                 (LAST_INSERT_ID(), 'LARGE');


INSERT INTO products (product_name, photo_url, subcategory_id)
VALUES ('Budo Band IJF - black', 'https://www.budokas.gr/wp-content/uploads/2022/02/ijf_budoband_zwart_met_ijf_logo_1.jpg', 4);

INSERT INTO product_sizes (product_id, size) VALUES
                                                 (LAST_INSERT_ID(), 'SMALL'),
                                                 (LAST_INSERT_ID(), 'MEDIUM'),
                                                 (LAST_INSERT_ID(), 'LARGE');

INSERT INTO products (product_name, photo_url, subcategory_id)
VALUES ('Budo Band IJF - brown', 'https://www.matsuru.com/pub/media/catalog/product/cache/7d361c3e738cad9ccd3190ce570ef8b4/i/j/ijf_budoband_bruin_met_ijf_logo.jpg', 4);

INSERT INTO product_sizes (product_id, size) VALUES
                                                 (LAST_INSERT_ID(), 'SMALL'),
                                                 (LAST_INSERT_ID(), 'MEDIUM'),
                                                 (LAST_INSERT_ID(), 'LARGE');

INSERT INTO products (product_name, photo_url, subcategory_id)
VALUES ('Checkered Band', 'https://www.matsuru.com/pub/media/catalog/product/cache/c9127b3a1b0fc7cc580cf429fd7e8a97/0/2/021928.jpg', 4);

INSERT INTO product_sizes (product_id, size) VALUES
                                                 (LAST_INSERT_ID(), 'SMALL'),
                                                 (LAST_INSERT_ID(), 'MEDIUM'),
                                                 (LAST_INSERT_ID(), 'LARGE');

INSERT INTO products (product_name, photo_url, subcategory_id)
VALUES ('Budo Band', 'https://www.matsuru.com/pub/media/catalog/product/cache/7d361c3e738cad9ccd3190ce570ef8b4/0/2/020120.jpg', 4);

INSERT INTO product_sizes (product_id, size) VALUES
                                                 (LAST_INSERT_ID(), 'SMALL'),
                                                 (LAST_INSERT_ID(), 'MEDIUM'),
                                                 (LAST_INSERT_ID(), 'LARGE');

INSERT INTO products (product_name, photo_url, subcategory_id)
VALUES ('Sports bag JUDO camouflage', 'https://www.matsuru.com/pub/media/catalog/product/cache/7d361c3e738cad9ccd3190ce570ef8b4/c/a/camouflage_tas_judo_zwart-grijs_lr.jpg', 5);

INSERT INTO product_sizes (product_id, size) VALUES
                                                 (LAST_INSERT_ID(), 'ONE_SIZE');

INSERT INTO products (product_name, photo_url, subcategory_id)
VALUES ('Sports bag JUDO black/red', 'https://www.matsuru.com/pub/media/catalog/product/cache/7d361c3e738cad9ccd3190ce570ef8b4/c/a/camouflage_tas_judo_zwart-rood_lr.jpg', 5);

INSERT INTO product_sizes (product_id, size) VALUES
    (LAST_INSERT_ID(), 'ONE_SIZE');

INSERT INTO products (product_name, photo_url, subcategory_id)
VALUES ('Large JUDO - black/orange', 'https://www.matsuru.com/pub/media/catalog/product/cache/7d361c3e738cad9ccd3190ce570ef8b4/h/o/hong_ming_judo_groot_zwart_oranje_2.jpg', 5);

INSERT INTO product_sizes (product_id, size) VALUES
    (LAST_INSERT_ID(), 'ONE_SIZE');

INSERT INTO products (product_name, photo_url, subcategory_id)
VALUES ('Backpack Judo Fabric Heavy', 'https://www.matsuru.com/pub/media/catalog/product/cache/7d361c3e738cad9ccd3190ce570ef8b4/r/u/rugzak_judostofheavy_wit.png', 5);

INSERT INTO product_sizes (product_id, size) VALUES
    (LAST_INSERT_ID(), 'ONE_SIZE');

INSERT INTO products (product_name, photo_url, subcategory_id)
VALUES ('Sports bag Judo', 'https://www.matsuru.com/pub/media/catalog/product/cache/7d361c3e738cad9ccd3190ce570ef8b4/3/4/343316-1.jpg', 5);

INSERT INTO product_sizes (product_id, size) VALUES
    (LAST_INSERT_ID(), 'ONE_SIZE');


INSERT INTO products (product_name, photo_url, subcategory_id)
VALUES ('Black Belt', 'https://www.budokas.gr/wp-content/uploads/2022/02/ijf_budoband_zwart_met_ijf_logo_1.jpg', 9);

INSERT INTO product_sizes (product_id, size) VALUES
                                                 (LAST_INSERT_ID(), 'SMALL'),
                                                 (LAST_INSERT_ID(), 'MEDIUM'),
                                                 (LAST_INSERT_ID(), 'LARGE');

INSERT INTO products (product_name, photo_url, subcategory_id)
VALUES ('Black Belt', 'https://www.budokas.gr/wp-content/uploads/2022/02/ijf_budoband_zwart_met_ijf_logo_1.jpg', 14);

INSERT INTO product_sizes (product_id, size) VALUES
                                                 (LAST_INSERT_ID(), 'SMALL'),
                                                 (LAST_INSERT_ID(), 'MEDIUM'),
                                                 (LAST_INSERT_ID(), 'LARGE');

INSERT INTO products (product_name, photo_url, subcategory_id)
VALUES ('Boxing Gloves', 'https://www.budokas.gr/wp-content/uploads/2022/03/classic_boxing_glove.jpg', 3);

INSERT INTO product_sizes (product_id, size) VALUES
                                                 (LAST_INSERT_ID(), 'OZ_10'),
                                                 (LAST_INSERT_ID(), 'OZ_12'),
                                                 (LAST_INSERT_ID(), 'OZ_14');

INSERT INTO products (product_name, photo_url, subcategory_id)
VALUES ('Boxing Gloves', 'https://www.budokas.gr/wp-content/uploads/2022/03/classic_boxing_glove.jpg', 8);

INSERT INTO product_sizes (product_id, size) VALUES
                                                 (LAST_INSERT_ID(), 'OZ_10'),
                                                 (LAST_INSERT_ID(), 'OZ_12'),
                                                 (LAST_INSERT_ID(), 'OZ_14');

INSERT INTO products (product_name, photo_url, subcategory_id)
VALUES ('Boxing Gloves', 'https://www.budokas.gr/wp-content/uploads/2022/03/classic_boxing_glove.jpg', 13);

INSERT INTO product_sizes (product_id, size) VALUES
                                                 (LAST_INSERT_ID(), 'OZ_10'),
                                                 (LAST_INSERT_ID(), 'OZ_12'),
                                                 (LAST_INSERT_ID(), 'OZ_14');


