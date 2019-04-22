import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { SysProductImage } from 'app/shared/model/sys-product-image.model';
import { SysProductImageService } from './sys-product-image.service';
import { SysProductImageComponent } from './sys-product-image.component';
import { SysProductImageDetailComponent } from './sys-product-image-detail.component';
import { SysProductImageUpdateComponent } from './sys-product-image-update.component';
import { SysProductImageDeletePopupComponent } from './sys-product-image-delete-dialog.component';
import { ISysProductImage } from 'app/shared/model/sys-product-image.model';

@Injectable({ providedIn: 'root' })
export class SysProductImageResolve implements Resolve<ISysProductImage> {
    constructor(private service: SysProductImageService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<SysProductImage> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<SysProductImage>) => response.ok),
                map((sysProductImage: HttpResponse<SysProductImage>) => sysProductImage.body)
            );
        }
        return of(new SysProductImage());
    }
}

export const sysProductImageRoute: Routes = [
    {
        path: 'sys-product-image',
        component: SysProductImageComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysProductImage.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-product-image/:id/view',
        component: SysProductImageDetailComponent,
        resolve: {
            sysProductImage: SysProductImageResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysProductImage.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-product-image/new',
        component: SysProductImageUpdateComponent,
        resolve: {
            sysProductImage: SysProductImageResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysProductImage.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-product-image/:id/edit',
        component: SysProductImageUpdateComponent,
        resolve: {
            sysProductImage: SysProductImageResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysProductImage.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const sysProductImagePopupRoute: Routes = [
    {
        path: 'sys-product-image/:id/delete',
        component: SysProductImageDeletePopupComponent,
        resolve: {
            sysProductImage: SysProductImageResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysProductImage.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
