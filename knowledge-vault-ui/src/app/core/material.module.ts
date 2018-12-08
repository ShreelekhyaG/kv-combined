import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
    MatButtonModule, MatCardModule, MatDialogModule, MatInputModule, MatTableModule, MatSidenavModule,
    MatToolbarModule, MatMenuModule, MatIconModule, MatProgressSpinnerModule, MatExpansionModule, MatListModule,
    MatGridListModule, MatProgressBarModule, MatFormFieldModule
} from '@angular/material';

@NgModule({
    imports: [
        CommonModule,
        MatToolbarModule,
        MatButtonModule,
        MatCardModule,
        MatInputModule,
        MatDialogModule,
        MatTableModule,
        MatMenuModule,
        MatIconModule,
        MatExpansionModule,
        MatProgressSpinnerModule,
        MatListModule,
        MatSidenavModule,
        MatProgressBarModule,
        MatFormFieldModule
    ],
    exports: [
        CommonModule,
        MatToolbarModule,
        MatButtonModule,
        MatCardModule,
        MatInputModule,
        MatDialogModule,
        MatTableModule,
        MatMenuModule,
        MatIconModule,
        MatListModule,
        MatSidenavModule,
        MatExpansionModule,
        MatProgressSpinnerModule,
        MatGridListModule,
        MatProgressBarModule,
        MatFormFieldModule
    ],
})
export class CustomMaterialModule { }
