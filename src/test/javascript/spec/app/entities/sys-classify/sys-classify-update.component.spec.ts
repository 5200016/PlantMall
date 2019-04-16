/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { PlantMallTestModule } from '../../../test.module';
import { SysClassifyUpdateComponent } from 'app/entities/sys-classify/sys-classify-update.component';
import { SysClassifyService } from 'app/entities/sys-classify/sys-classify.service';
import { SysClassify } from 'app/shared/model/sys-classify.model';

describe('Component Tests', () => {
    describe('SysClassify Management Update Component', () => {
        let comp: SysClassifyUpdateComponent;
        let fixture: ComponentFixture<SysClassifyUpdateComponent>;
        let service: SysClassifyService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysClassifyUpdateComponent]
            })
                .overrideTemplate(SysClassifyUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SysClassifyUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SysClassifyService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new SysClassify(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.sysClassify = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new SysClassify();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.sysClassify = entity;
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
