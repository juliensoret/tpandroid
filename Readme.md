# TP Android - App Bar

## Introduction
*App bar* est le nom de la barre d'action d'une application Android. Elle est aussi appelée *action bar*.
Il y a plusieurs façon de créer une *app bar* pour son application : notamment via le composant **`ActionBar`** qui est inclut par défaut dans plusieurs thèmes. 
Mais depuis Android 5.0, un nouveau composant nommé **`Toolbar`** est apparu et reprend toutes les fonctionnalités de l'ActionBar (et d'autres). Ce composant est aussi, grâce à la librairie de support v7 AppCompat, compatible avec un plus grand nombre de versions Android.

> **ActionBar** est disponible depuis Android 3.0 mais n'a cessé d'être modifié et son apparence est désormais changeante d'une version à l'autre.
> **Toolbar** est quant-à-lui un composant identique sur toutes les versions qui peuvent utiliser v7 AppCompat. (Apparence et comportement identique dès Android 2.1)

## Installation de la Toolbar

 - **Étape 1** : Ajouter la librairie de support v7 AppCompat à votre projet. *(voir [documentation officielle](https://developer.android.com/topic/libraries/support-library/setup.html))*
 - **Étape 2** : Vérifier que l'activité étends `AppCompatActivity`.
```java
public class MainActivity extends AppCompatActivity { ... }
```
 - **Étape 3** : Utiliser un thème qui ne fournit pas déjà de `ActionBar`.
(Dans `app/src/main/res/values/styles.xml`) :
```xml
android:theme="@style/Theme.AppCompat.Light.NoActionBar"
```
 - **Étape 4** : Ajouter le composant Toolbar dans le layout de l'activité. (A placer en premier car nous allons l'utiliser ici en tant que *app bar*)
```xml
<LinearLayout ...>
	<android.support.v7.widget.Toolbar
	    android:id="@+id/my_toolbar"
	    android:layout_width="match_parent"
	    android:layout_height="?attr/actionBarSize"
	    android:background="?attr/colorPrimary"
	    android:elevation="4dp"
	    android:theme="@style/ToolbarTheme"
	    app:popupTheme="@style/ThemeOverlay.AppCompat.Light"/>
...
</LinearLayout>
```
 - **Étape 5** : Ajouter le *namespace* `app` dans le `Layout` parent de ce même fichier.
```xml
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
 ```
 - **Étape 6** : Définir la `Toolbar` dans la méthode `onCreate()` de l'activité.
```java
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
    setSupportActionBar(myToolbar);
}
```
Vous devriez désormais avoir une barre qui apparait en haut de votre application. Celle-ci contient par défaut le nom de votre application ainsi qu'un bouton de menu "options supplémentaires". Ce menu ne contient que l'action "Settings", nous allons voir comment en ajouter dans la suite de ce TP.

## Méthodes utiles de la barre
Une fois que la `Toolbar`est définie comme *app bar* de votre application, vous avez accès à toutes les méthodes de la classe [`ActionBar`](https://developer.android.com/reference/android/support/v7/app/ActionBar.html) .
Pour utiliser ces méthodes, il faut utiliser la méthode `getSupportActionBar()`de l'activité. 
Par exemple, pour cacher la barre : 
```java
getSupportActionBar().hide()
```
## Ajouter des actions

Il est possible d'ajouter des boutons à cette *app bar*. Cependant, l'espace étant limité, l'application mettra les actions qui n'ont pas de place dans le menu "options supplémentaires" automatiquement. Il est possible de définir nous-même quelles actions doivent s'afficher ou non obligatoirement.

 - **Étape 1** : Créer un menu dans les ressources.
Dans `res/menu/toolbar_menu.xml` :
```xml
<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto">

    <item android:id="@+id/action_edit"
        android:icon="@android:drawable/ic_menu_edit"
        android:title="Editer"
        app:showAsAction="always"/>

    <item
        android:id="@+id/action_save"
        android:icon="@android:drawable/ic_menu_save"
        android:title="Sauvegarder"
        app:showAsAction="ifRoom"/>

    <item android:id="@+id/action_settings"
        android:title="Paramètres"
        app:showAsAction="never"/>

</menu>
```

> `showAsAction` permet de définir si une action doit être toujours affichée en tant qu'icône dans la barre (`always`), seulement si il y a suffisamment de place (`ifRoom`) ou bien si elle doit toujours être dans le menu des options supplémentaires (`never`).

 - **Étape 2** : Ajouter le menu à l'*app bar* dans l'activité.
 
```java
public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.toolbar_menu, menu);
    return true;
}
 ```
 
 - **Étape 3** : Gérer la sélection des actions
 
 Quand un bouton de l'*app bar* est selectionné, la méthode `onOptionsItemSelected()` de l'activité est appelée avec un `MenuItem` en paramètre qui renseigne quel bouton. On utilise la méthode `MenuItem.getItemId()` qui renvoit l'id qu'on a définit dans `toolbar_menu.xml`.
 
```java
 @Override
 public boolean onOptionsItemSelected(MenuItem item) {
     switch (item.getItemId()) {
         case R.id.action_settings:
             Toast.makeText(this, "Option Paramètres selectionnée", Toast.LENGTH_SHORT).show();
             return true;

         case R.id.action_edit:
             Toast.makeText(this, "Option Editer selectionnée", Toast.LENGTH_SHORT).show();
             return true;

         case R.id.action_save:
             Toast.makeText(this, "Option Sauvegarder selectionnée", Toast.LENGTH_SHORT).show();
             return true;

         default:
             return super.onOptionsItemSelected(item);
     }
 }
```
 
## Conclusion

Nous voilà avec une *app bar* fonctionnelle qui contient plusieurs actions simples avec les bases pour les gérer .
Le code final qui inclut les ajouts du TP est disponible dans ce *repository*, dans la branche `version1`.

```bash
git clone https://github.com/juliensoret/tpandroid.git
git checkout version1
```
Vous pouvez aussi consulter un récapitulatif des ajouts nécessaires en comparant le code initial au code final avec Github par ce lien : https://github.com/juliensoret/tpandroid/compare/version1 .

## Liens

- https://developer.android.com/training/appbar/index.html (Officiel)
- http://mathias-seguy.developpez.com/tutoriels/android/utiliser-toolbar/ 

