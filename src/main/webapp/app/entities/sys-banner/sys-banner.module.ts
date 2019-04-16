import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PlantMallSharedModule } from 'app/shared';
import {
    SysBannerComponent,
    SysBannerDetailComponent,
    SysBannerUpdateComponent,
    SysBannerDeletePopupComponent,
    SysBannerDeleteDialogComponent,
    sysBannerRoute,
    sysBannerPopupRoute
} from './';

const ENTITY_STATES = [...sysBannerRoute, ...sysBannerPopupRoute];

@NgModule({
    imports: [PlantMallSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SysBannerComponent,
        SysBannerDetailComponent,
        SysBannerUpdateComponent,
        SysBannerDeleteDialogComponent,
        SysBannerDeletePopupComponent
    ],
    entryComponents: [SysBannerComponent, SysBannerUpdateComponent, SysBannerDeleteDialogComponent, SysBannerDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PlantMallSysBannerModule {}
