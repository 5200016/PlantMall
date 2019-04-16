/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { PlantMallTestModule } from '../../../test.module';
import { SysBannerUpdateComponent } from 'app/entities/sys-banner/sys-banner-update.component';
import { SysBannerService } from 'app/entities/sys-banner/sys-banner.service';
import { SysBanner } from 'app/shared/model/sys-banner.model';

describe('Component Tests', () => {
    describe('SysBanner Management Update Component', () => {
        let comp: SysBannerUpdateComponent;
        let fixture: ComponentFixture<SysBannerUpdateComponent>;
        let service: SysBannerService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysBannerUpdateComponent]
            })
                .overrideTemplate(SysBannerUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SysBannerUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SysBannerService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new SysBanner(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.sysBanner = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new SysBanner();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.sysBanner = entity;
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
