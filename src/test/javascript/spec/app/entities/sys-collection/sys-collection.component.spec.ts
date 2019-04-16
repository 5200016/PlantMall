/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { PlantMallTestModule } from '../../../test.module';
import { SysCollectionComponent } from 'app/entities/sys-collection/sys-collection.component';
import { SysCollectionService } from 'app/entities/sys-collection/sys-collection.service';
import { SysCollection } from 'app/shared/model/sys-collection.model';

describe('Component Tests', () => {
    describe('SysCollection Management Component', () => {
        let comp: SysCollectionComponent;
        let fixture: ComponentFixture<SysCollectionComponent>;
        let service: SysCollectionService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysCollectionComponent],
                providers: []
            })
                .overrideTemplate(SysCollectionComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SysCollectionComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SysCollectionService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new SysCollection(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.sysCollections[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
