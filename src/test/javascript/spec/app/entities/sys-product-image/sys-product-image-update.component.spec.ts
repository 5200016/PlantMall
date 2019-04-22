/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { PlantMallTestModule } from '../../../test.module';
import { SysProductImageUpdateComponent } from 'app/entities/sys-product-image/sys-product-image-update.component';
import { SysProductImageService } from 'app/entities/sys-product-image/sys-product-image.service';
import { SysProductImage } from 'app/shared/model/sys-product-image.model';

describe('Component Tests', () => {
    describe('SysProductImage Management Update Component', () => {
        let comp: SysProductImageUpdateComponent;
        let fixture: ComponentFixture<SysProductImageUpdateComponent>;
        let service: SysProductImageService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysProductImageUpdateComponent]
            })
                .overrideTemplate(SysProductImageUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SysProductImageUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SysProductImageService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new SysProductImage(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.sysProductImage = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new SysProductImage();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.sysProductImage = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.create).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));
        });
    });
});
